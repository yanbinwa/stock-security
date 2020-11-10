package com.yanbin.stock.stocksecurityservice.security.filter;

import com.yanbin.stock.stocksecurityservice.service.AdminService;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminConstants;
import com.yanbin.stock.stocksecurityutils.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/10 上午8:37
 */
@Component
public class SetUserToHeaderFilter implements Filter {

    @Autowired
    AdminService adminService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if ((servletRequest instanceof HttpServletRequest)) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HeaderRequestWrapper headerRequestWrapper = new HeaderRequestWrapper(httpRequest);
            // TODO 目前会被调用两次，有点奇怪
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
                String userName = authentication.getName();
                User user = adminService.getUser(userName);
                if (user != null) {
                    headerRequestWrapper.putHeader(StockAdminConstants.HEADER_USER_ID_KEY, String.valueOf(user.getId()));
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private class HeaderRequestWrapper extends HttpServletRequestWrapper {

        private final Map<String, String> customHeaders;

        HeaderRequestWrapper(HttpServletRequest request) {
            super(request);
            this.customHeaders = new HashMap<>();
        }

        void putHeader(String name, String value){
            this.customHeaders.put(name, value);
        }

        public String getHeader(String name) {
            String headerValue = customHeaders.get(name);

            if (headerValue != null){
                return headerValue;
            }
            return ((HttpServletRequest) getRequest()).getHeader(name);
        }

        public Enumeration<String> getHeaderNames() {
            Set<String> set = new HashSet<>(customHeaders.keySet());

            Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
            while (e.hasMoreElements()) {
                String n = e.nextElement();
                set.add(n);
            }

            return Collections.enumeration(set);
        }
    }
}

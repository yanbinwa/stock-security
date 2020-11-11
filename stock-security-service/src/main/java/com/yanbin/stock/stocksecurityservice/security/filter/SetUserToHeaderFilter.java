package com.yanbin.stock.stocksecurityservice.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yanbin.stock.stocksecurityservice.service.AdminService;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminConstants;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminResponseInfo;
import com.yanbin.stock.stocksecurityutils.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/10 上午8:37
 */
@Component
public class SetUserToHeaderFilter extends ZuulFilter {

    @Autowired
    AdminService adminService;


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();
            User user = adminService.getUser(userName);
            if (user != null) {
                ctx.addZuulRequestHeader(StockAdminConstants.HEADER_USER_ID_KEY, String.valueOf(user.getId()));
            }
        }
        return null;
    }
}

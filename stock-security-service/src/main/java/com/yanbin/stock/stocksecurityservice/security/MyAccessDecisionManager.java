package com.yanbin.stock.stocksecurityservice.security;

import com.yanbin.stock.stocksecurityservice.service.AdminService;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminConstants;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午6:23
 *
 * 判定权限是否生效，这里指API
 *
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    AdminService adminService;

    /**
     * @param authentication  当前的状态，是否已登录
     * @param o
     * @param collection   资源所需的attribute
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String attributeName = configAttribute.getAttribute();
            // 只需要登录就可以使用的资源
            if (StockAdminConstants.ATTRIBUTE_LOGIN.equals(attributeName)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException(StockAdminResponseInfo.USER_NOT_LOGIN.getMessage());
                } else {
                    return;
                }
            }
            // 获取当前用户权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (attributeName.equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(StockAdminResponseInfo.USER_NO_AUTHORITY.getMessage());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

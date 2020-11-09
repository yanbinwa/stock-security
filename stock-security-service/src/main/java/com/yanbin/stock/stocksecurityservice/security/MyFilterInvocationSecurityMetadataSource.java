package com.yanbin.stock.stocksecurityservice.security;

import com.yanbin.stock.stocksecurityservice.service.AdminService;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminConstants;
import com.yanbin.stock.stocksecurityutils.pojo.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午6:15
 *
 * 获取请求Api所需的权限信息
 *
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    AdminService adminService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if (StockAdminConstants.LOGIN_URL.equals(requestUrl) || StockAdminConstants.SIGN_IN_URL.equals(requestUrl)) {
            return null;
        }
        List<Attribute> attributeList = adminService.getAttributeByApi(requestUrl);
        if (CollectionUtils.isEmpty(attributeList)) {
            // 需要登录后才可以调用
            return SecurityConfig.createList(StockAdminConstants.ATTRIBUTE_LOGIN);
        } else {
            return SecurityConfig.createList(
                    attributeList.stream().map(Attribute::getName).collect(Collectors.toList()).toArray(new String[0]));
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

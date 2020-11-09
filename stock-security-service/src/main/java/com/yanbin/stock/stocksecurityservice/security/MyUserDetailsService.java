package com.yanbin.stock.stocksecurityservice.security;

import com.yanbin.stock.stocksecurityservice.service.AdminService;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminResponseInfo;
import com.yanbin.stock.stocksecurityutils.pojo.Attribute;
import com.yanbin.stock.stocksecurityutils.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午5:09
 *
 * 提供认证和授权用户获取接口
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = adminService.getUser(s);
        if (user == null) {
            throw new UsernameNotFoundException(StockAdminResponseInfo.USER_NOT_EXIST.getMessage());
        }
        if (user.getEnable() != null && !user.getEnable()) {
            throw new RuntimeException(StockAdminResponseInfo.USER_DISABLE.getMessage());
        }
        List<Attribute> attributeList = adminService.getAttributeByUserId(user.getId());
        // 组装成UserDetails
        return buildUserDetails(user, attributeList);
    }

    private UserDetails buildUserDetails(User user, List<Attribute> attributeList) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(attributeList)) {
            attributeList.forEach(t -> grantedAuthorities.add(new SimpleGrantedAuthority(t.getName())));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
    }
}

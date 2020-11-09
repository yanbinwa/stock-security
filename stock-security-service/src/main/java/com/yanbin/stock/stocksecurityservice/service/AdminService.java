package com.yanbin.stock.stocksecurityservice.service;

import com.yanbin.stock.stocksecurityutils.exception.StockAdminException;
import com.yanbin.stock.stocksecurityutils.pojo.Attribute;
import com.yanbin.stock.stocksecurityutils.pojo.User;
import com.yanbin.stock.stocksecurityutils.request.UserRoleRequest;

import java.util.List;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午5:10
 *
 * 包括了用户，角色，权限功能
 *
 */
public interface AdminService {
    // 用户
    User addUser(User user) throws StockAdminException;
    User getUser(String name);

    // 角色
    void updateUserRole(Long userId, UserRoleRequest userRoleRequest);

    // 权限
    List<Attribute> getAttributeByUserId(Long userId);
    List<Attribute> getAttributeByApi(String apiUrl);
}

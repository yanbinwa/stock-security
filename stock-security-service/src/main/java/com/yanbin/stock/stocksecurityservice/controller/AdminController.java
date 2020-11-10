package com.yanbin.stock.stocksecurityservice.controller;

import com.yanbin.stock.stocksecurityservice.service.AdminService;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminConstants;
import com.yanbin.stock.stocksecurityutils.exception.StockAdminException;
import com.yanbin.stock.stocksecurityutils.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/5 上午8:47
 *
 * 1. 用户注册
 * 2. 用户登录
 * 3. 用户状态验证 cookie验证（在前端进入的时候，需要做验证，如果异常，返回到登录页面）
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping(StockAdminConstants.ADMIN_BASE_URL)
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public void login() {

    }

    @PostMapping("/logout")
    public void logout() {

    }

    @PostMapping("/signIn")
    public void signIn(@RequestBody User user) throws StockAdminException {
        adminService.addUser(user);
    }

    @GetMapping("/check")
    public void check() {
        log.info("check");
    }

}


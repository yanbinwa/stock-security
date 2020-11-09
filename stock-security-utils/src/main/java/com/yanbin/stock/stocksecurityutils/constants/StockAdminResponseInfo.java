package com.yanbin.stock.stocksecurityutils.constants;

import lombok.Getter;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:32
 */
@Getter
public enum StockAdminResponseInfo {

    USER_NAME_DUPLICATE(8001, "用户名已存在"),
    USER_DISABLE(8002, "用户失效"),
    USER_NOT_EXIST(8003, "用户不存在"),
    USER_NOT_LOGIN(8004, "用户未登录"),
    USER_NO_AUTHORITY(8005, "用户没有权限"),
    USER_PASSWORD_NOT_CORRECT(8006, "密码错误"),
    ;

    // 返回code
    private int code;
    // 返回消息
    private String message;

    StockAdminResponseInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

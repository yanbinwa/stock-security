package com.yanbin.stock.stocksecurityservice.security.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午7:55
 *
 * 用户登录成功的Listener
 *
 */
@Component
public class MyLoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {

    }
}

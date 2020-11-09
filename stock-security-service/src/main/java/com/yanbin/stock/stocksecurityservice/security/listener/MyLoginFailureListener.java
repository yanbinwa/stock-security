package com.yanbin.stock.stocksecurityservice.security.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午7:55
 *
 * 如果多次失败，用户会锁死，可以通过redis来记录锁死的用户，给与一个过期时间
 */
@Component
public class MyLoginFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

    }

}

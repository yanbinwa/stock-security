package com.yanbin.stock.stocksecurityservice.security.hander;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午7:48
 *
 *
 * 权限异常处理逻辑，改写返回数据
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        // 需要判断是因为未登录，还是因为权限不够导致

        log.error("access denied");
    }
}

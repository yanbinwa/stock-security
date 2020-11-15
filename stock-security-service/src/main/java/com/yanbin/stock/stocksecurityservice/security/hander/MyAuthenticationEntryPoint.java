package com.yanbin.stock.stocksecurityservice.security.hander;

import com.emotibot.gemini.geminiutils.pojo.response.Response;
import com.emotibot.gemini.geminiutils.utils.JsonUtils;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/8 上午8:53
 *
 * 判断用户是否登录
 */
@Slf4j
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("user or password not correct");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Response response = new Response(StockAdminResponseInfo.USER_NOT_LOGIN.getCode(), StockAdminResponseInfo.USER_NOT_LOGIN.getMessage(), null);
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().write(JsonUtils.getStrFromObject(response));
    }
}

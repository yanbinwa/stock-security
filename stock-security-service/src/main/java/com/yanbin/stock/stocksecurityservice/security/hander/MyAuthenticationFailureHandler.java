package com.yanbin.stock.stocksecurityservice.security.hander;

import com.emotibot.gemini.geminiutils.pojo.response.Response;
import com.emotibot.gemini.geminiutils.utils.JsonUtils;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午7:52
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("authentication failure");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Response response = new Response(StockAdminResponseInfo.USER_PASSWORD_NOT_CORRECT.getCode(), StockAdminResponseInfo.USER_PASSWORD_NOT_CORRECT.getMessage(), null);
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().write(JsonUtils.getStrFromObject(response));
    }
}

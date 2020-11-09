package com.yanbin.stock.stocksecurityservice.security;

import com.yanbin.stock.stocksecurityservice.security.hander.*;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/5 上午8:52
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAccessDecisionManager myAccessDecisionManager;

    @Autowired
    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    /**
     *  密码验证
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(StockAdminConstants.LOGIN_URL, StockAdminConstants.SIGN_IN_URL).permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginProcessingUrl(StockAdminConstants.LOGIN_URL).usernameParameter("userName")
                .passwordParameter("password").permitAll().successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .logout().logoutUrl(StockAdminConstants.LOGOUT_URL).logoutSuccessHandler(myLogoutSuccessHandler)
                .and()
                .csrf().disable().exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .and()
                .rememberMe().rememberMeParameter(StockAdminConstants.REMEMBER_ME);
    }
}

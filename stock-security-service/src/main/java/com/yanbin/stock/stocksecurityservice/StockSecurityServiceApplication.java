package com.yanbin.stock.stocksecurityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class StockSecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockSecurityServiceApplication.class, args);
    }

}

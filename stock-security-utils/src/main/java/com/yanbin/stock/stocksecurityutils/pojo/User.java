package com.yanbin.stock.stocksecurityutils.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String password;
    private Boolean enable;
}

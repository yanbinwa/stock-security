package com.yanbin.stock.stocksecurityutils.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:05
 *
 * 预先定义在数据库中的，没有增删改查
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
    private Long id;
    private String name;
    private List<String> menuList;
    private List<String> apiList;
}

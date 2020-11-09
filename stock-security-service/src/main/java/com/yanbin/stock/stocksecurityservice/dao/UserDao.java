package com.yanbin.stock.stocksecurityservice.dao;

import com.yanbin.stock.stocksecurityservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:14
 */
public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByName(String name);
}

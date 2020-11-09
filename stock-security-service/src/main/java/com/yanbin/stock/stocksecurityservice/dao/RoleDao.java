package com.yanbin.stock.stocksecurityservice.dao;

import com.yanbin.stock.stocksecurityservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:21
 */
public interface RoleDao extends JpaRepository<RoleEntity, Long> {

}

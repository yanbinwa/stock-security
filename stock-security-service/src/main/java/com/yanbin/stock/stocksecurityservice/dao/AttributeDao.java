package com.yanbin.stock.stocksecurityservice.dao;

import com.yanbin.stock.stocksecurityservice.entity.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:20
 */
public interface AttributeDao extends JpaRepository<AttributeEntity, Long> {

    @Query("SELECT ae FROM AttributeEntity ae WHERE ae.id in " +
            "(SELECT DISTINCT rrae.attributeId FROM RelRoleAttributeEntity rrae JOIN RelUserRoleEntity rure " +
            "ON rrae.roleId = rrae.roleId WHERE rure.userId = ?1)")
    List<AttributeEntity> findAllByUserId(Long userId);

}

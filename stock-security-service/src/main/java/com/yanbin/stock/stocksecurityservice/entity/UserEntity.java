package com.yanbin.stock.stocksecurityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/5 上午8:49
 *
 * 用户名不能重复，需要唯一
 *
 * 数据库存放用户密码是加密过的
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "enable")
    private Boolean enable;
    @Column(name = "create_time")
    @CreatedDate
    private Timestamp createTime;
    @Column(name = "update_time")
    @LastModifiedDate
    private Timestamp updateTime;
}

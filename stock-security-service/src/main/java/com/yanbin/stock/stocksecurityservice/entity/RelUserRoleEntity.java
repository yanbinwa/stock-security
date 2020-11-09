package com.yanbin.stock.stocksecurityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午5:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="rel_user_role")
@EntityListeners(AuditingEntityListener.class)
public class RelUserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;
}

package com.yanbin.stock.stocksecurityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午6:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="rel_role_attribute")
@EntityListeners(AuditingEntityListener.class)
public class RelRoleAttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "attribute_id")
    private Long attributeId;
}

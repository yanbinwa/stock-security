package com.yanbin.stock.stocksecurityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午5:16
 *
 * 权限表，权限的type有两种，一种是给前端的，对应于前端的菜单，一种是给后端的，对应于后端的api
 * 对于一个权限，可以对应多个menu和api
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="attribute")
@EntityListeners(AuditingEntityListener.class)
public class AttributeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "menu_list_str")
    private String menuListStr;
    @Column(name = "api_list_str")
    private String apiListStr;
}

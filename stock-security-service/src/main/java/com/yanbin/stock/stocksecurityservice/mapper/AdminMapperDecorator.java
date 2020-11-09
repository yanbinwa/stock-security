package com.yanbin.stock.stocksecurityservice.mapper;

import com.yanbin.stock.stocksecurityservice.entity.AttributeEntity;
import com.yanbin.stock.stocksecurityutils.pojo.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:38
 */
@Component
public abstract class AdminMapperDecorator implements AdminMapper {

    @Autowired
    AdminMapper adminMapper;

    public Attribute attributeEntityToAttribute(AttributeEntity attributeEntity) {
        Attribute attribute = adminMapper.attributeEntityToAttribute(attributeEntity);
        if (!StringUtils.isEmpty(attributeEntity.getMenuListStr())) {
            attribute.setMenuList(Arrays.asList(attributeEntity.getMenuListStr().split(",")));
        }
        if (!StringUtils.isEmpty(attributeEntity.getApiListStr())) {
            attribute.setMenuList(Arrays.asList(attributeEntity.getApiListStr().split(",")));
        }
        return attribute;
    }

    public AttributeEntity attributeToAttributeEntity(Attribute attribute) {
        AttributeEntity attributeEntity = adminMapper.attributeToAttributeEntity(attribute);
        if (!CollectionUtils.isEmpty(attribute.getApiList())) {
            attributeEntity.setMenuListStr(String.join(",", attribute.getMenuList()));
        }
        if (!CollectionUtils.isEmpty(attribute.getApiList())) {
            attributeEntity.setApiListStr(String.join(",", attribute.getApiList()));
        }
        return attributeEntity;
    }
}

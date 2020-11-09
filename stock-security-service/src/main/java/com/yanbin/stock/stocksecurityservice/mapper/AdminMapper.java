package com.yanbin.stock.stocksecurityservice.mapper;

import com.yanbin.stock.stocksecurityservice.entity.AttributeEntity;
import com.yanbin.stock.stocksecurityservice.entity.UserEntity;
import com.yanbin.stock.stocksecurityutils.pojo.Attribute;
import com.yanbin.stock.stocksecurityutils.pojo.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午8:38
 */
@Mapper(componentModel = "spring")
@DecoratedWith(AdminMapperDecorator.class)
public interface AdminMapper {
    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);

    Attribute attributeEntityToAttribute(AttributeEntity attributeEntity);
    AttributeEntity attributeToAttributeEntity(Attribute attribute);
}

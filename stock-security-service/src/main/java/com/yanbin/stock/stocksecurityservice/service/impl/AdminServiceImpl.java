package com.yanbin.stock.stocksecurityservice.service.impl;

import com.yanbin.stock.stocksecurityservice.dao.AttributeDao;
import com.yanbin.stock.stocksecurityservice.dao.UserDao;
import com.yanbin.stock.stocksecurityservice.entity.AttributeEntity;
import com.yanbin.stock.stocksecurityservice.entity.UserEntity;
import com.yanbin.stock.stocksecurityservice.mapper.AdminMapper;
import com.yanbin.stock.stocksecurityservice.service.AdminService;
import com.yanbin.stock.stocksecurityutils.constants.StockAdminResponseInfo;
import com.yanbin.stock.stocksecurityutils.exception.StockAdminException;
import com.yanbin.stock.stocksecurityutils.pojo.Attribute;
import com.yanbin.stock.stocksecurityutils.pojo.User;
import com.yanbin.stock.stocksecurityutils.request.UserRoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yanbinwang@emotibot.com
 * @date 2020/11/7 下午6:17
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    UserDao userDao;

    @Autowired
    AttributeDao attributeDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 1. 检查用户是否已存在
     * 2. 需要将编码过的密码写入到数据库中
     *
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) throws StockAdminException {
        UserEntity userEntity = userDao.findOneByName(user.getName());
        if (userEntity != null) {
            throw new StockAdminException(StockAdminResponseInfo.USER_NAME_DUPLICATE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity = adminMapper.userToUserEntity(user);
        userDao.save(userEntity);
        return adminMapper.userEntityToUser(userEntity);
    }

    @Override
    public User getUser(String name) {
        UserEntity userEntity = userDao.findOneByName(name);
        if (userEntity == null) {
            return null;
        }
        return adminMapper.userEntityToUser(userEntity);
    }

    @Override
    public void updateUserRole(Long userId, UserRoleRequest userRoleRequest) {

    }

    @Override
    public List<Attribute> getAttributeByUserId(Long userId) {
        List<AttributeEntity> attributeEntityList = attributeDao.findAllByUserId(userId);
        if (CollectionUtils.isEmpty(attributeEntityList)) {
            return null;
        }
        return attributeEntityList.stream().map(t -> adminMapper.attributeEntityToAttribute(t)).collect(Collectors.toList());
    }

    /**
     * 获取所有api与attribute的映射关系，按照api对应的
     *
     * @param apiUrl
     * @return
     */
    @Override
    public List<Attribute> getAttributeByApi(String apiUrl) {
        List<AttributeEntity> attributeEntityList = attributeDao.findAll();
        if (CollectionUtils.isEmpty(attributeEntityList)) {
            return null;
        }
        List<Attribute> attributeList = attributeEntityList.stream().map(t -> adminMapper.attributeEntityToAttribute(t))
                .collect(Collectors.toList());
        Map<String, List<Attribute>> apiToAttributeListMap = new HashMap<>();
        attributeList.stream().filter(t -> !CollectionUtils.isEmpty(t.getApiList())).forEach(t -> t.getApiList().forEach(t1 -> {
            List<Attribute> attributes = apiToAttributeListMap.get(t1);
            if (attributes == null) {
                attributes = new ArrayList<>();
            }
            attributes.add(t);
        }));
        List<String> apiList = apiToAttributeListMap.keySet().stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
        for (String api : apiList) {
            // TODO 需要优化，不只是startWith
            if (apiUrl.startsWith(api)) {
                return apiToAttributeListMap.get(api);
            }
        }
        return null;
    }
}

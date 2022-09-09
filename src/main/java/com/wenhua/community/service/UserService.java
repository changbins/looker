package com.wenhua.community.service;

/*
 * @Author:ChangBins
 * @Data:2022-09-07  14:09
 * @Description:community-com.wenhua.community.service
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.dao.UserMapper;
import com.wenhua.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;
    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

}

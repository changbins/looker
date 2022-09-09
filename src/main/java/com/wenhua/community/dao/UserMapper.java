package com.wenhua.community.dao;

/*
 * @Author:ChangBins
 * @Data:2022-09-07  11:34
 * @Description:community-com.wenhua.community.dao
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);
}

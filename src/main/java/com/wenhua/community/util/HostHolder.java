package com.wenhua.community.util;

/*
 * @Author:ChangBins
 * @Data:2022-09-12  14:39
 * @Description:community-com.wenhua.community.util
 * @Version：1.0
 * @Detail：持有用户信息，用于代替session对象
 * */

import com.wenhua.community.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    /**
     * 存入用户信息
     *
     * @param user 用户信息
     */
    public void setUsers(User user) {
        users.set(user);
    }

    /**
     * 取出用户信息
     *
     * @return 返回用户信息
     */
    public User getUser() {
        return users.get();
    }

    /**
     * 清理用户信息
     */
    public void clear(){
        users.remove();
    }
}

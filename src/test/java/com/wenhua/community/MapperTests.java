package com.wenhua.community;


import com.wenhua.community.dao.DiscussPostMapper;
import com.wenhua.community.dao.LoginTicketMapper;
import com.wenhua.community.dao.UserMapper;
import com.wenhua.community.entity.DiscussPost;
import com.wenhua.community.entity.LoginTicket;
import com.wenhua.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

/*
 * @Author:ChangBins
 * @Data:2022-09-07  13:34
 * @Description:community-com.wenhua.community.entity
 * @Version：1.0
 * @Detail：
 * */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private DiscussPostMapper discussPostMapper;

    @Autowired(required = false)
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);
        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "hello");
        System.out.println(rows);
    }

    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for(DiscussPost post : list) {
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

    @Test
    public void testInsertTicket(){
        LoginTicket LT1 = new LoginTicket();
        LT1.setId(101);
        LT1.setTicket("jiayi");
        LT1.setStatus(1);
        LT1.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 *10));
        loginTicketMapper.insertLoginTicket(LT1);
    }
    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("jiayi");
        System.out.println(loginTicket);
        loginTicketMapper.updateStatus("jiayi", 0);
        loginTicket = loginTicketMapper.selectByTicket("jiayi");
        System.out.println(loginTicket);
    }

}

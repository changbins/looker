package com.wenhua.community;


import com.wenhua.community.dao.*;
import com.wenhua.community.entity.*;
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

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MessageMapper messageMapper;

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
    @Test
    public void testDiscussPostInsert(){
        DiscussPost post = new DiscussPost();
        post.setTitle("bug消失");
        post.setContent("bug消失");
        post.setCreateTime(new Date());

        int i = discussPostMapper.insertDiscussPost(post);
        System.out.println(i);
    }


    @Test
    public void addComment(){
        Comment comment = new Comment();
        comment.setUserId(111);
        comment.setEntityType(1);
        comment.setEntityId(228);
        comment.setTargetId(0);
        comment.setStatus(0);
        comment.setContent("这是一个测试数据");
        comment.setCreateTime(new Date());
        int insertComment = commentMapper.insertComment(comment);
        System.out.println("成功插入记录"+insertComment);
    }

    @Test
    public void testMessageMapper(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for (Message message : messages) {
            System.out.println(message);
        }

        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);

        List<Message> letters = messageMapper.selectLetters("111_112", 0, 10);
        for (Message letter : letters) {
            System.out.println(letter);
        }

        int lettersCount = messageMapper.selectLettersCount("111_112");
        System.out.println(lettersCount);

        int unreadCount = messageMapper.selectLetterUnreadCount(131,"111_131");
        System.out.println(unreadCount);
    }
}

package com.wenhua.community;

/*
 * @Author:ChangBins
 * @Data:2022-09-08  14:56
 * @Description:community-com.wenhua.community
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class mailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired(required = false)
    private TemplateEngine templateEngine;

    @Test
    public void testTestMail() {
        mailClient.sendMail("Wu.changbin@outlook.com", "java邮件测试", "测试成功，这是编程中涉及到的邮件");
    }

    @Test
    public void testHtmlmail() {
        Context context = new Context();
        context.setVariable("username","sandy");
        String process = templateEngine.process("/mail/Demo", context);
        System.out.println(process);

        mailClient.sendMail("sunny_wwu@163.com","测试",process);
    }

}

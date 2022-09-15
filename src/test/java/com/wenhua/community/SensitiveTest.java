package com.wenhua.community;

/*
 * @Author:ChangBins
 * @Data:2022-09-14  9:48
 * @Description:community-com.wenhua.community
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTest {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text = "能不能电话交友？不能贩卖鸦片";
        String filter = sensitiveFilter.filter(text);
        System.out.println(filter);
    }
}

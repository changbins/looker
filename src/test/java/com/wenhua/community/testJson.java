package com.wenhua.community;

import com.wenhua.community.util.CommunityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

/*
 * @Author:ChangBins
 * @Data:2022-09-14  13:34
 * @Description:community-com.wenhua.community
 * @Version：1.0
 * @Detail：
 * */

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class testJson {

    @Autowired(required = false)
    private CommunityUtil communityUtil;

    @Test
    public void testJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",25);
        System.out.println(CommunityUtil.getJsonString(0, "ok",map));
    }
}

package com.wenhua.community.controller;

/*
 * @Author:ChangBins
 * @Data:2022-09-14  14:39
 * @Description:community-com.wenhua.community.controller
 * @Version：1.0
 * @Detail：发布帖子的controller
 * */

import com.wenhua.community.entity.DiscussPost;
import com.wenhua.community.entity.User;
import com.wenhua.community.service.DiscussPostService;
import com.wenhua.community.util.CommunityUtil;
import com.wenhua.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 发布 帖子
     *
     * @param title   帖子标题
     * @param content 帖子内容
     * @return 发布结果，发布是否成功
     */
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJsonString(403, "你还没有登录哦!");
        }

        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        // 报错的情况,将来统一处理.
        return CommunityUtil.getJsonString(0, "发布成功!");
    }

}

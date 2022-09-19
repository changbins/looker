package com.wenhua.community.controller;

/*
 * @Author:ChangBins
 * @Data:2022-09-19  19:10
 * @Description:community-com.wenhua.community.controller
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.entity.Comment;
import com.wenhua.community.service.CommentService;
import com.wenhua.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 处理添加评论
     *
     * @param discussPostId 评论者的id
     * @param comment       评论内容
     * @return 评论页的模板
     */
    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        return "redirect:/discuss/detail/" + discussPostId;
    }
}

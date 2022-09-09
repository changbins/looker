package com.wenhua.community.service;

/*
 * @Author:ChangBins
 * @Data:2022-09-07  13:55
 * @Description:community-com.wenhua.community.service
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.dao.DiscussPostMapper;
import com.wenhua.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired(required = false)
    private DiscussPostMapper discussPostMapper;


    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}

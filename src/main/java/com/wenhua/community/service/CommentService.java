package com.wenhua.community.service;

/*
 * @Author:ChangBins
 * @Data:2022-09-16  8:44
 * @Description:community-com.wenhua.community.service
 * @Version：1.0
 * @Detail：
 * */

import com.wenhua.community.dao.CommentMapper;
import com.wenhua.community.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    public int findCommentCount(int entityType, int entityId) {
        return commentMapper.selectCountByEntity(entityType, entityId);

    }
}

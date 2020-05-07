package com.xuzi.share.service.impl;

import com.xuzi.share.dao.CommentMapper;
import com.xuzi.share.entity.Comment;
import com.xuzi.share.service.CommentService;
import com.xuzi.share.utils.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CommentserviceImpl implements CommentService,CommunityConstant {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    @Override
    public int findCommentCount(int entityType, int entityId) {
        return commentMapper.selectCountByEntity(entityType, entityId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 添加评论
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(comment.getContent());
        int rows = commentMapper.insertComment(comment);

        return rows;
    }
    @Override
    public Comment findCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }
}

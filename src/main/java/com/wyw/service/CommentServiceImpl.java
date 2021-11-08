package com.wyw.service;

import com.wyw.dao.CommentMapper;
import com.wyw.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int deleteCommentByIdAmdWord(Map map) {
        return commentMapper.deleteCommentByIdAmdWord(map);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    @Override
    public List<Comment> QuerryAllComment() {
        return commentMapper.QuerryAllComment();
    }

    @Override
    public List<Comment> QuerryCommentById(int cId) {
        return commentMapper.QuerryCommentById(cId);
    }

    @Override
    public Comment SpecificQuerryCommentByIdAndWord(Map map) {
        return commentMapper.SpecificQuerryCommentByIdAndWord(map);
    }
}

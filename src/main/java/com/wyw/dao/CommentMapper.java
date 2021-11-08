package com.wyw.dao;

import com.wyw.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    int addComment(Comment comment);
    int deleteCommentByIdAmdWord(Map map);
    int updateComment(Comment comment);
    List<Comment> QuerryAllComment();
    List<Comment> QuerryCommentById(int cId);
    Comment SpecificQuerryCommentByIdAndWord(Map map);
}

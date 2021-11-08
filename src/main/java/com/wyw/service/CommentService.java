package com.wyw.service;

import com.wyw.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    int addComment(Comment comment);
    int deleteCommentByIdAmdWord(Map map);
    int updateComment(Comment comment);
    List<Comment> QuerryAllComment();
    List<Comment> QuerryCommentById(int cId);
    Comment SpecificQuerryCommentByIdAndWord(Map map);
}

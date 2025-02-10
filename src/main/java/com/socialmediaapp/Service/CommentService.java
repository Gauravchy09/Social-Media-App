package com.socialmediaapp.Service;

import com.socialmediaapp.Entity.Comment;

public interface CommentService {

    public Comment createComment(Comment comment, int postId, int userId) throws Exception;

    public Comment likeComment(Integer commentId, int userId) throws Exception;

    public Comment findCommentById(int commentId) throws Exception;
}

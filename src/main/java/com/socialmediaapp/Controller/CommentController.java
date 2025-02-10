package com.socialmediaapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaapp.Entity.Comment;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Service.CommentService;
import com.socialmediaapp.Service.UserService;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,@PathVariable int postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment comment2 = commentService.createComment(comment,postId,user.getId());
        return comment2;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,@PathVariable int commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment comment2 = commentService.likeComment(commentId, user.getId());
        return comment2;
    }

}

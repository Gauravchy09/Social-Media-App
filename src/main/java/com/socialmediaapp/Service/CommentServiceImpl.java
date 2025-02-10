package com.socialmediaapp.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Comment;
import com.socialmediaapp.Entity.Post;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.CommentRepository;
import com.socialmediaapp.Repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, int postId, int userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        postRepository.save(post);
        return savedComment;
    }

    @Override
    public Comment likeComment(Integer commentId, int userId) throws Exception {
        User user = userService.findUserById(userId);
        Comment comment = findCommentById(commentId);
        if(!comment.getLiked().contains(user)) {
            comment.getLiked().add(user);
        }
        else {
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(int commentId) throws Exception {
        Optional<Comment> opt =  commentRepository.findById(commentId);

        if(opt.isEmpty()) {
            throw new Exception("comment not found with id: "+commentId);
        }
        return opt.get();
    }

}

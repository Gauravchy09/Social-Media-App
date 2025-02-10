package com.socialmediaapp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Post;

@Service
public interface PostService {

    Post createNewPost(Post post, Integer userId) throws Exception;

    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId);

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPost();

    Post savedPost(int postId, int userId) throws Exception;

    Post likePost(int postId, int userId) throws Exception;
}

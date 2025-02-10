package com.socialmediaapp.Service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialmediaapp.Entity.Post;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.PostRepository;
import com.socialmediaapp.Repository.UserRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserService userService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (!post.getUser().getId().equals(user.getId())) {
            throw new Exception("You can't delete another user's post");
        }

        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        return postRepository.findById(postId)
                .orElseThrow(() -> new Exception("Post not found with id: " + postId));
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(int postId, int userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost() == null) {
            throw new Exception("Saved posts list is not initialized");
        }

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }

        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(int postId, int userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked() == null) {
            throw new Exception("Liked list is not initialized");
        }

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }
}

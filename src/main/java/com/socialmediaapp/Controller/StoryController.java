package com.socialmediaapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.socialmediaapp.Entity.Story;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Service.StoryService;
import com.socialmediaapp.Service.UserService;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/story/create")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);

        return storyService.createStory(story, user);
    }


    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable int userId, @RequestHeader("Authorization")String jwt) throws Exception{
        User reqUser = userService.findUserByJwt(jwt);
        List<Story> stories = storyService.findStoryByUserId(reqUser.getId());

        return stories;
    }
}

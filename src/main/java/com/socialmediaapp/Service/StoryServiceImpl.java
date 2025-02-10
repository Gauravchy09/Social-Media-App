package com.socialmediaapp.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Story;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) throws Exception {
        
        Story newStory = new Story();
        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setUser(user);
        newStory.setTimeStamp(LocalDateTime.now());
        return storyRepository.save(newStory);
        
    }

    @Override
    public List<Story> findStoryByUserId(int userId) throws Exception {
        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(user.getId());
    }

}

package com.socialmediaapp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Story;
import com.socialmediaapp.Entity.User;

@Service
public interface StoryService {

    public Story createStory(Story story, User user) throws Exception;

    public List<Story> findStoryByUserId(int userId) throws Exception;


}

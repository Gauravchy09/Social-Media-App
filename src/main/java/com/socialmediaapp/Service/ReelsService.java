package com.socialmediaapp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Reels;
import com.socialmediaapp.Entity.User;

@Service
public interface ReelsService {

    public Reels creatReels(Reels reels, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(int userId) throws Exception;
}

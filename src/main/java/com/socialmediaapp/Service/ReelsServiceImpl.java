package com.socialmediaapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaapp.Entity.Reels;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.ReelsRepository;

@Service
public class ReelsServiceImpl implements ReelsService{

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels creatReels(Reels reels, User user) {
        Reels newReels = new Reels();
        newReels.setTitle(reels.getTitle());
        newReels.setUser(user);
        newReels.setVideo(reels.getVideo());
        return reelsRepository.save(newReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(int userId) throws Exception {
        User user = userService.findUserById(userId);
        List<Reels> reels = reelsRepository.findByUserId(user.getId());
        return reels;
    }

}

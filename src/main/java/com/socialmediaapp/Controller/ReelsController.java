package com.socialmediaapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaapp.Entity.Reels;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Service.ReelsService;
import com.socialmediaapp.Service.UserService;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels/create")
    public Reels creatReels(@RequestBody Reels reels, @RequestHeader("Authorization")String jwt) {
        User user = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.creatReels(reels,user);
        return createdReels;
    }

    @GetMapping("/api/reels")
    public List<Reels> getAllReels() {
        return reelsService.findAllReels();
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable int userId) throws Exception {
        List<Reels> reels = reelsService.findUsersReel(userId);
        return reels;
    }
}

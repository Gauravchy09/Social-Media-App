package com.socialmediaapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaapp.Entity.User;

import com.socialmediaapp.Service.UserService;


import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/getall")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("user/get/{userId}")
    public User getUserById(@PathVariable int userId) throws Exception {
        return userService.findUserById(userId);
    }

    @PutMapping("/api/user/update")
    public User updateUser(@RequestBody User user,@RequestHeader("Authorization") String jwt) throws Exception {

        User loggdeInUser = userService.findUserByJwt(jwt);
        return userService.updateUser(user, loggdeInUser.getId());
    }

    @PutMapping("/api/user/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable  int userId2) throws Exception{
        User user1 = userService.findUserByJwt(jwt);

        return userService.followUser(user1.getId(), userId2);
    }

    @GetMapping("/api/user/search")
    public List<User> searchUser(@RequestParam("query")String query) {
        return userService.searchUser(query);
    }

    @GetMapping("/api/user/profile")
    public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
        System.out.println("jwt ------ "+jwt);
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }

}


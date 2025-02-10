package com.socialmediaapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaapp.Config.JwtProvider;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());
        newUser.setGender(user.getGender());
        
        User savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(int userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        throw new Exception("user not found with id: "+userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(int userId1, int userId2) throws Exception {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user1.getFollowings().add(user2.getId());
        user2.getFollowers().add(user1.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user, int userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);
        if(user1.isEmpty()) {
            throw new Exception("user not exists");
        }
        User olduser = user1.get();
        if(user.getFirstName() != null) {
            olduser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            olduser.setLastName(user.getLastName());
        }
        if(user.getEmail() != null) {
            olduser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null) {
            olduser.setPassword(user.getPassword());
        }
        if(user.getGender() != null) {
            olduser.setGender(user.getGender());
        }
        return userRepository.save(olduser);
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }

}

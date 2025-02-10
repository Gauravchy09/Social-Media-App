package com.socialmediaapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.socialmediaapp.Config.JwtProvider;
import com.socialmediaapp.Entity.User;
import com.socialmediaapp.Repository.UserRepository;
import com.socialmediaapp.Service.CustomUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public AuthResponse createUser (@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());
        if(isExist != null) {
            throw new Exception("email already exist with another account");
        }
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setGender(user.getGender());
        
        userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token,"Register Success");
        return authResponse;
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest) throws Exception{

        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"Register Success");
        return res;
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if(userDetails == null) {
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("password not matched !");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
    }
     
            

}

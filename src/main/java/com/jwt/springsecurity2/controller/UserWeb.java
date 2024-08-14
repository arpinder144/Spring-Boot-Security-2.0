package com.jwt.springsecurity2.controller;

import com.jwt.springsecurity2.model.UserInfo;
import com.jwt.springsecurity2.model.UserModel;
import com.jwt.springsecurity2.service.UserDetailsServiceImpl;
import com.jwt.springsecurity2.service.UserService;
import com.jwt.springsecurity2.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserWeb {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signup")
    public UserInfo registerUser(@RequestBody UserModel userModel){
        return userService.registerUser(userModel);
    }

   @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel userModel){
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword()));

           UserDetails userDetails = userDetailsService.loadUserByUsername(userModel.getUsername());
           String jwt = jwtUtil.generateToken(userDetails.getUsername());
           return new ResponseEntity<>(jwt, HttpStatus.OK);
       }catch (Exception e){
           log.error("Exception occurred while create Authentication token",e);
           return new ResponseEntity<>("Incorrect Username or Password",HttpStatus.BAD_REQUEST);

       }


    }






    @GetMapping("/check")
    public String healthCheck(){
        return ("Working fine!!");
    }
}


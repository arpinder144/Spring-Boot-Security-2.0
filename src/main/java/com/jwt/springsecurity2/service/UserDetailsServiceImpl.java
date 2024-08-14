package com.jwt.springsecurity2.service;

import com.jwt.springsecurity2.model.UserInfo;
import com.jwt.springsecurity2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepo.findUserInfosByUsername(username);
        if(userInfo!=null){
//            this user is coming from UserDetails
            return User.builder()
                    .username(userInfo.getUsername())
                    .password(userInfo.getPassword())
                    .roles(userInfo.getRole())
                    .build();
        }else {
            throw new UsernameNotFoundException("User not found with username:"+username);
        }

    }
}

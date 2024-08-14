package com.jwt.springsecurity2.service;

import com.jwt.springsecurity2.model.UserInfo;
import com.jwt.springsecurity2.model.UserModel;
import com.jwt.springsecurity2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserInfo registerUser(UserModel useModel){
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(useModel.getUsername());
        userInfo.setPassword(passwordEncoder.encode(useModel.getPassword()));
        userInfo.setRole(useModel.getRole());
        return userRepo.save(userInfo);
    }
}

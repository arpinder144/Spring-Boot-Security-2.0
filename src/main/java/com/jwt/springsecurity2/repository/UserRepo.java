package com.jwt.springsecurity2.repository;

import com.jwt.springsecurity2.model.UserInfo;
import com.jwt.springsecurity2.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,Long> {
    UserInfo findUserInfosByUsername(String username);
}

package com.jwt.springsecurity2.model;

import lombok.Data;

@Data
public class UserModel {
    private String username;
    private String password;
    private String role;
}

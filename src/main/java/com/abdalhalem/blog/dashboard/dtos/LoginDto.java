package com.abdalhalem.blog.dashboard.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
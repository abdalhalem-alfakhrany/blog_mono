package com.abdalhalem.blog.dashboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdalhalem.blog.dashboard.dtos.LoginDto;
import com.abdalhalem.blog.dashboard.dtos.RegisterUserDto;
import com.abdalhalem.blog.dashboard.response.LoginResponse;
import com.abdalhalem.blog.dashboard.services.AuthenticationService;
import com.abdalhalem.blog.dashboard.services.JwtService;
import com.abdalhalem.blog.model.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        User authenticatedUser = authenticationService.authenticate(loginDto);
        LoginResponse response = LoginResponse.builder()
                .token(jwtService.generateToken(authenticatedUser))
                .expiresIn(jwtService.getJwtExpiration())
                .build();
        return ResponseEntity.ok(response);
    }
}

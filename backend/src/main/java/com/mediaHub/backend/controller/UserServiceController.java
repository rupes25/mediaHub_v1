package com.mediaHub.backend.controller;

import com.mediaHub.backend.dto.LoginReq;
import com.mediaHub.backend.dto.LoginRes;
import com.mediaHub.backend.dto.RegisterReq;
import com.mediaHub.backend.dto.RegisterRes;
import com.mediaHub.backend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserServiceController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterRes> register(@RequestBody @Valid RegisterReq req, HttpServletResponse response){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(req,response));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes>login(@RequestBody @Valid LoginReq req,HttpServletResponse response){
        return ResponseEntity.ok(userService.login(req,response));
    }

    @PostMapping("/logout")
    public ResponseEntity<String>logout(HttpServletResponse response){
        userService.logout(response);
        return ResponseEntity.ok("Logout successfully.");
    }



}

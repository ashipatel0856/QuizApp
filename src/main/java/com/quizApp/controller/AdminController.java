package com.quizApp.controller;

import com.quizApp.dto.LoginRequestDto;
import com.quizApp.dto.SignupRequestDto;
import com.quizApp.entity.Admin;
import com.quizApp.service.AdminService;
import com.quizApp.service.JwtService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;

    }

    @PostMapping("/signup")
    public ResponseEntity<Admin> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        Admin admin = adminService.signUp(signupRequestDto);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginRequestDto loginRequestDto) throws BadRequestException {
        String token = adminService.Login(loginRequestDto);
        return ResponseEntity.ok(token);
    }

}

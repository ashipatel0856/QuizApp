package com.quizApp.service;

import com.quizApp.dto.LoginRequestDto;
import com.quizApp.dto.SignupRequestDto;
import com.quizApp.entity.Admin;
import org.apache.coyote.BadRequestException;

public interface AdminService {
    Admin signUp(SignupRequestDto signupRequestDto);
    String Login(LoginRequestDto loginRequestDto) throws BadRequestException;

}

package com.quizApp.service;


import com.quizApp.dto.LoginRequestDto;
import com.quizApp.dto.SignupRequestDto;
import com.quizApp.entity.Admin;
import com.quizApp.exceptions.BadRequestException;
import com.quizApp.repository.AdminRepository;

import com.quizApp.utils.BCrypt;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class AdminService {

    private static final Logger log = Logger.getLogger(AdminService.class.getName());
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    public AdminService(AdminRepository adminRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public Admin signUp(SignupRequestDto signupRequestDto){
        log.info("signup a user with email: {}"+signupRequestDto.getEmail());

        boolean exists = adminRepository.existsByEmail(signupRequestDto.getEmail());
        Admin admin = modelMapper.map(signupRequestDto, Admin.class);
        admin.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        admin=adminRepository.save(admin);

        return modelMapper.map(admin, Admin.class);
    }


    public String login(LoginRequestDto loginRequestDto){
        log.info("login a user with email: {}"+loginRequestDto.getEmail());
        Admin admin = adminRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new BadRequestException("incorrect eamil or password"));

        boolean isPasswordMatch = BCrypt.hash(loginRequestDto.getPassword()).equals(admin.getPassword());
        if(isPasswordMatch){
            throw new BadRequestException("incorrect email or password");
        }
        return jwtService.generateAccessToken(admin);
    }
}

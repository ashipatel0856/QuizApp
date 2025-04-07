package com.quizApp.service;

import com.quizApp.dto.LoginRequestDto;
import com.quizApp.dto.SignupRequestDto;
import com.quizApp.entity.Admin;
import com.quizApp.repository.AdminRepository;

import com.quizApp.utils.BCrypt;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger log = Logger.getLogger(AdminServiceImpl.class.getName());
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @Override
    public Admin signUp(SignupRequestDto signupRequestDto){
        log.info("signup a user with email: {}"+signupRequestDto.getEmail());

        boolean exists = adminRepository.existsByEmail(signupRequestDto.getEmail());
        Admin admin = modelMapper.map(signupRequestDto, Admin.class);
        admin.setPassword(BCrypt.hash(signupRequestDto.getPassword()));

        admin=adminRepository.save(admin);

        return modelMapper.map(admin, Admin.class);
    }

    @Override
    public String Login(LoginRequestDto loginRequestDto) throws BadRequestException {
        log.info("login request for admin with email: {}"+loginRequestDto.getEmail());

        Admin admin = adminRepository.findByEmail(loginRequestDto.getEmail());

        boolean isPasswordMatch = BCrypt.match(loginRequestDto.getPassword(), admin.getPassword());
        if(!isPasswordMatch) {
            throw new BadRequestException("Incorrect email or password");
        }
        return jwtService.generateAccessToken(admin);
    }


}

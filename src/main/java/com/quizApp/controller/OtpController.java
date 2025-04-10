package com.quizApp.controller;

import com.quizApp.dto.OtpRequestDTO;
import com.quizApp.dto.OtpResponseDTO;
import com.quizApp.dto.OtpVerifyDTO;
import com.quizApp.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {



    private OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public ResponseEntity<OtpResponseDTO> generateOtp(@RequestBody OtpRequestDTO otpRequestDTO) {
        return ResponseEntity.ok(otpService.generateOtp(otpRequestDTO));
    }

    @PostMapping("/verify")
    public ResponseEntity<OtpResponseDTO> verifyOtp(@RequestBody OtpVerifyDTO otpVerifyDTO) {
        return ResponseEntity.ok(otpService.verifyOtp(otpVerifyDTO));
    }

}

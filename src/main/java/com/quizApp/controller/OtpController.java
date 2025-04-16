package com.quizApp.controller;

import com.quizApp.dto.OtpRequestDTO;
import com.quizApp.dto.OtpResponseDTO;
import com.quizApp.dto.OtpVerifyDTO;
import com.quizApp.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {



    private OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping(value = "/generate", produces = "application/json")
    public ResponseEntity<OtpResponseDTO> generateOtp(@RequestBody OtpRequestDTO otpRequestDTO) {
        OtpResponseDTO otpResponse = otpService.generateOtp(otpRequestDTO);
        return ResponseEntity.ok(otpResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<OtpResponseDTO> verifyOtp(@RequestBody OtpVerifyDTO otpVerifyDTO) {
        OtpResponseDTO otpResponse = otpService.verifyOtp(otpVerifyDTO);
        return ResponseEntity.ok(otpResponse);
    }

}

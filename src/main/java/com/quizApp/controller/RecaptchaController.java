package com.quizApp.controller;

import com.quizApp.dto.reCaptchaRequestDto;
import com.quizApp.security.RecaptchaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RecaptchaController {


    private final RecaptchaService recaptchaService;

    public RecaptchaController(RecaptchaService recaptchaService) {
        this.recaptchaService = recaptchaService;
    }

    @PostMapping("/captcha")
    public ResponseEntity<?> signUp(@RequestBody reCaptchaRequestDto captcha) {

        boolean isCaptchaValid = recaptchaService.isCaptchaValid(captcha.getReCaptchaToken());

        if (!isCaptchaValid) {
            return ResponseEntity.badRequest().body("Captcha verification failed");
        }

        // Continue signup (save user etc)
        return ResponseEntity.ok("Signup successful!");
    }
}

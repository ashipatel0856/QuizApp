package com.quizApp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RecaptchaService {

    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    public boolean isCaptchaValid(String token) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", recaptchaSecret);
        params.add("response", token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            Map<String, Object> body = response.getBody();

            if (body == null) {
                System.out.println("reCAPTCHA response body is null.");
                return false;
            }

            Object successObj = body.get("success");

            if (successObj instanceof Boolean) {
                return (Boolean) successObj;
            } else {
                System.out.println("Unexpected response from Google reCAPTCHA: " + body);
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error verifying reCAPTCHA: " + e.getMessage());
            return false;
        }
    }
}

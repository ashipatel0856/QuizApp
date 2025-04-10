package com.quizApp.dto;

import lombok.Data;

@Data
public class OtpResponseDTO {

    private String message;

    public OtpResponseDTO(String message) {
        this.message = message;
    }
}

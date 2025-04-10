package com.quizApp.dto;

import lombok.Data;

@Data
public class OtpRequestDTO {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

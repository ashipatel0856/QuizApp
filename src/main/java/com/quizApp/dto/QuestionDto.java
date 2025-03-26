package com.quizApp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

}

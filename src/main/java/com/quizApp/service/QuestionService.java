package com.quizApp.service;

import com.quizApp.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    QuestionDto createQuestion(QuestionDto questionDto);
    QuestionDto getQuestionById(Long id);
    List<QuestionDto> getAllQuestions();
    QuestionDto updateQuestionById( Long id ,QuestionDto questionDto);
    void deleteQuestionById(Long id);
}

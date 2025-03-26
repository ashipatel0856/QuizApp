package com.quizApp.service;

import com.quizApp.dto.QuizDto;

import java.util.List;

public interface QuizService {

    QuizDto createQuiz(QuizDto quizDto);
    List<QuizDto> getAllQuizzes();
    QuizDto getQuizById(Long id);
    QuizDto updateQuizById(Long id, QuizDto quizDto);
    void deleteQuizById(Long id);
}

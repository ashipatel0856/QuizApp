package com.quizApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
public class QuestionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questions;
    private String questionsText;

    @ElementCollection
    private List<String> options;
    private String correctAnswer;
    private String category;
    private String difficulty;
}

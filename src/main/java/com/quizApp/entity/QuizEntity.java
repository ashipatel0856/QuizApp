package com.quizApp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizId;
    private String adminId;
    private String quizTitle;
    private boolean status;
    private List<String> players;

    private List<QuestionsEntity> questions;
    private List<Map<String,QuestionsEntity>> question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public List<QuestionsEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsEntity> questions) {
        this.questions = questions;
    }

    public List<Map<String, QuestionsEntity>> getQuestion() {
        return question;
    }

    public void setQuestion(List<Map<String, QuestionsEntity>> question) {
        this.question = question;
    }
}

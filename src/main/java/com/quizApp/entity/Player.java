package com.quizApp.entity;

import jakarta.persistence.*;

import java.util.List;

//@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;

    @ManyToOne
    @JoinTable(
            name = "player_quizzes",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )

    private List<Quiz> attemptedQuizzes;
}

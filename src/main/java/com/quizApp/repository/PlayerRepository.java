package com.quizApp.repository;

import com.quizApp.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByQuizId(String quizId);
    Optional<Player> findByEmail(String email);

}

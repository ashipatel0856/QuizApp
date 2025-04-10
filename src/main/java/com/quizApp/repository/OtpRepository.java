package com.quizApp.repository;

import com.quizApp.entity.Otp;
import com.quizApp.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findTopByPlayerOrderByExpiryTimeDesc(Player player);
}

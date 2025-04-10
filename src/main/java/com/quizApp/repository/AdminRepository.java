package com.quizApp.repository;

import com.quizApp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);


    Optional<Admin> findByEmail(String eamil);


}

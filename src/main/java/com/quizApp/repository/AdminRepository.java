package com.quizApp.repository;

import com.quizApp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);

    Admin findByEmail(String email);
}

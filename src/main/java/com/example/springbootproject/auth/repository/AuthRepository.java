package com.example.springbootproject.auth.repository;

import com.example.springbootproject.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepository extends JpaRepository<User,Long> {
    List<User> findByEmail(String email);
}

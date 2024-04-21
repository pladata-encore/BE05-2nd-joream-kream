package com.example.springbootproject.auth.repository;

import com.example.springbootproject.auth.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth,Long> {
}

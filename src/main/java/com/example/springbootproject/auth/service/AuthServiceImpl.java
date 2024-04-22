package com.example.springbootproject.auth.service;

import com.example.springbootproject.auth.domain.User;
import com.example.springbootproject.auth.dto.request.SignupRequest;
import com.example.springbootproject.auth.excrption.AuthErrorCode;
import com.example.springbootproject.auth.excrption.AuthException;
import com.example.springbootproject.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    @Override
    public void signup(SignupRequest signupRequest) {
        String encoded = passwordEncoder.encode(signupRequest.password());
        if(!authRepository.findByEmail(signupRequest.email()).isEmpty())
            throw new AuthException(AuthErrorCode.EMAIL_DUPLICATED);
        User entity = signupRequest.toEntity(encoded);
        authRepository.save(entity);
    }
}

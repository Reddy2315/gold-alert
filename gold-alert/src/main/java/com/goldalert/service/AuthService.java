package com.goldalert.service;

import com.goldalert.dto.LoginRequest;
import com.goldalert.dto.RegisterRequest;
import com.goldalert.exception.AuthException;
import com.goldalert.model.User;
import com.goldalert.repository.UserRepository;
import com.goldalert.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already registered");
                });

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new AuthException("Invalid email or password", "AUTH_INVALID")
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid email or password", "AUTH_INVALID");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}

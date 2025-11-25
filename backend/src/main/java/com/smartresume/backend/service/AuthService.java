package com.smartresume.backend.service;

import com.smartresume.backend.model.User;
import com.smartresume.backend.repository.UserRepository;
import com.smartresume.backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Map<String, String> register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of("token", token, "username", user.getUsername());
    }

    public Map<String, String> login(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(existingUser.getUsername());
        return Map.of("token", token, "username", existingUser.getUsername());
    }
}

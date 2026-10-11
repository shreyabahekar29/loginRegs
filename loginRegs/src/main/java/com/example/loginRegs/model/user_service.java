
package com.example.loginRegs.model;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class user_service {

    private final user_Repository userRepository;
    private final PasswordEncoder passwordEncoder;

    public user_service(
            user_Repository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(
            String fullName,
            String username,
            String email,
            String password,
            String confirmPassword) {

        if (fullName == null || fullName.isBlank()
                || username == null || username.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()) {
            return "All fields are required.";
        }

        if (!password.equals(confirmPassword)) {
            return "Passwords do not match.";
        }

        if (password.length() < 8) {
            return "Password must contain at least 8 characters.";
        }

        if (userRepository.existsByUsername(username)) {
            return "Username already exists.";
        }

        if (userRepository.existsByEmail(email)) {
            return "Email already exists.";
        }

        user_entity user = new user_entity();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        userRepository.save(user);

        return "SUCCESS";
    }
}

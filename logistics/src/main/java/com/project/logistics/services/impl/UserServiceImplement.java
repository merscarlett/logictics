package com.project.logistics.services.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.logistics.exception.InvalidPasswordException;
import com.project.logistics.exception.UserAlreadyExistsException;
import com.project.logistics.exception.UserNotFoundException;
import com.project.logistics.models.Users;
import com.project.logistics.repositories.UsersRepository;
import com.project.logistics.security.JWTTokenProvider;
import com.project.logistics.services.UserService;
import io.micrometer.observation.ObservationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final JWTTokenProvider jwtTokenProvider;

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Users signUp(Users newUser) {

        if (usersRepository.existsByUsername(newUser.getUsername())) {
            throw new UserAlreadyExistsException(
                    "Username %s is already in use".formatted(newUser.getUsername()));
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usersRepository.save(newUser);

        return newUser;
    }

    @Override
    public Optional<DecodedJWT> signIn(String username, String password) {
        var user = usersRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username %s not found".formatted(username))
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return jwtTokenProvider.toDecodedJWT(
                jwtTokenProvider.generateToken(user.getId(), username, user.getRole()));
    }
}

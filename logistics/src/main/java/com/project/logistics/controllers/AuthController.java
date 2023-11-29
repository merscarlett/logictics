package com.project.logistics.controllers;

import com.project.logistics.controllers.auth.Credentials;
import com.project.logistics.controllers.auth.JWTToken;
import com.project.logistics.models.Users;
import com.project.logistics.models.dto.UserCreationDto;
import com.project.logistics.models.dto.UserDto;
import com.project.logistics.models.mapper.JWTTokenMapper;
import com.project.logistics.models.mapper.UserMapper;
import com.project.logistics.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserMapper userMapper;

    private final JWTTokenMapper jwtTokenMapper;

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreationDto userCreationDto) {
        Users users = userService.signUp(userMapper.toUser(userCreationDto));

        return new ResponseEntity<>(userMapper.toUserDto(users), HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<JWTToken> signIn(@RequestBody Credentials credentials) {
        return ResponseEntity.of(userService
                .signIn(credentials.getUsername(), credentials.getPassword())
                .map(jwtTokenMapper::toPayload));
    }
}

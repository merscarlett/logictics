package com.project.logistics.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.logistics.models.Users;

import java.util.Optional;

public interface UserService {

    Users signUp(Users newUser);

    Optional<DecodedJWT> signIn(String username, String password);

}

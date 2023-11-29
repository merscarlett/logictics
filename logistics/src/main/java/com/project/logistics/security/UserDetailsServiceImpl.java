package com.project.logistics.security;

import com.project.logistics.exception.UserNotFoundException;
import com.project.logistics.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username %s not found".formatted(username)));

        return new UserDetailsImpl(user);
    }
}
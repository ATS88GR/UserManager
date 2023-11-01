package com.education.projects.users.manager.service;

import com.education.projects.users.manager.config.JwtService;
import com.education.projects.users.manager.dto.request.AuthenticationRequest;
import com.education.projects.users.manager.dto.request.UserDtoReq;
import com.education.projects.users.manager.dto.response.AuthenticationResponse;
import com.education.projects.users.manager.entity.User;
import com.education.projects.users.manager.repository.LevelRepository;
import com.education.projects.users.manager.repository.RoleRepository;
import com.education.projects.users.manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LevelRepository levelRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDtoReq request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(roleRepository.getReferenceById(request.getRoleId()))
                .level(levelRepository.getReferenceById(request.getLevelId()))
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}

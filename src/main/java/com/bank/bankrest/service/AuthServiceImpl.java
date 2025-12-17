package com.bank.bankrest.service;

import com.bank.bankrest.domain.model.Role;
import com.bank.bankrest.domain.model.User;
import com.bank.bankrest.domain.enums.RoleName;
import com.bank.bankrest.dto.request.auth.LoginRequest;
import com.bank.bankrest.dto.request.auth.RegisterRequest;
import com.bank.bankrest.dto.response.auth.AuthResponse;
import com.bank.bankrest.exception.BadRequestException;
import com.bank.bankrest.repository.RoleRepository;
import com.bank.bankrest.repository.UserRepository;
import com.bank.bankrest.security.jwt.JwtTokenProvider;
import com.bank.bankrest.service.impl.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        String token = jwtTokenProvider.generateToken(authentication);
//        System.out.println("LOGIN USER: " + request.getUsername());
//
//        User user = userRepository.findByUsername(request.getUsername()).get();
//
//        System.out.println("RAW: " + request.getPassword());
//        System.out.println("DB : " + user.getPassword());
//        System.out.println(
//                "MATCH: " + passwordEncoder.matches(
//                        request.getPassword(),
//                        user.getPassword()
//                )
//        );

        return new AuthResponse(token);
    }

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new BadRequestException("Role not found"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }
}

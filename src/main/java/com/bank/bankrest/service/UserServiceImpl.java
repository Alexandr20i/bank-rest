package com.bank.bankrest.service;

import com.bank.bankrest.mapper.UserMapper;
import com.bank.bankrest.repository.UserRepository;
import com.bank.bankrest.service.impl.UserService;
import com.bank.bankrest.dto.response.user.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }
}

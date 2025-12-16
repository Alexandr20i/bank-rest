package com.bank.bankrest.mapper;

import com.bank.bankrest.domain.model.User;
import com.bank.bankrest.dto.response.user.UserResponse;

public class UserMapper {

    private UserMapper() {}

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().getName().name());
        return response;
    }
}

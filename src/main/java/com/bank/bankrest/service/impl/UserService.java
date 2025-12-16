package com.bank.bankrest.service.impl;

import com.bank.bankrest.dto.response.user.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
}

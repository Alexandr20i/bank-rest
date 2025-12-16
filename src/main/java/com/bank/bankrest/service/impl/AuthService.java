package com.bank.bankrest.service.impl;

import com.bank.bankrest.dto.request.auth.LoginRequest;
import com.bank.bankrest.dto.request.auth.RegisterRequest;
import com.bank.bankrest.dto.response.auth.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    void register(RegisterRequest request);
}

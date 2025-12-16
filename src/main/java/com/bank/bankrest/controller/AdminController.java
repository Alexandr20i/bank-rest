package com.bank.bankrest.controller;

import com.bank.bankrest.dto.response.user.UserResponse;
import com.bank.bankrest.service.impl.CardService;
import com.bank.bankrest.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final CardService cardService;

    public AdminController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/cards/{cardId}/activate")
    public void activateCard(@PathVariable Long cardId) {
        cardService.activateCard(cardId);
    }

    @DeleteMapping("/cards/{cardId}")
    public void deleteCard(@PathVariable Long cardId) {
        // метод можно добавить в CardService при необходимости
    }
}

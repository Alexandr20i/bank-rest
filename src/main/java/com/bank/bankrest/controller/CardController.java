package com.bank.bankrest.controller;

import com.bank.bankrest.dto.request.card.CreateCardRequest;
import com.bank.bankrest.dto.response.card.CardResponse;
import com.bank.bankrest.security.model.CustomUserDetails;
import com.bank.bankrest.service.impl.CardService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public CardResponse createCard(
            @AuthenticationPrincipal CustomUserDetails user,
            @Valid @RequestBody CreateCardRequest request
    ) {
        return cardService.createCard(user.getId(), request);
    }

    @GetMapping
    public Page<CardResponse> getMyCards(
            @AuthenticationPrincipal CustomUserDetails user,
            Pageable pageable
    ) {
        return cardService.getUserCards(user.getId(), pageable);
    }

    @PostMapping("/{cardId}/block")
    public void blockCard(@PathVariable Long cardId) {
        cardService.blockCard(cardId);
    }
}

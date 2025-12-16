package com.bank.bankrest.service.impl;

import com.bank.bankrest.dto.request.card.CreateCardRequest;
import com.bank.bankrest.dto.response.card.CardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    CardResponse createCard(Long userId, CreateCardRequest request);

    Page<CardResponse> getUserCards(Long userId, Pageable pageable);

    void blockCard(Long cardId);

    void activateCard(Long cardId);
}

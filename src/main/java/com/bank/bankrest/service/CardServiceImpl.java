package com.bank.bankrest.service;

import com.bank.bankrest.domain.model.Card;
import com.bank.bankrest.domain.model.User;
import com.bank.bankrest.domain.enums.CardStatus;
import com.bank.bankrest.dto.request.card.CreateCardRequest;
import com.bank.bankrest.dto.response.card.CardResponse;
import com.bank.bankrest.exception.NotFoundException;
import com.bank.bankrest.mapper.CardMapper;
import com.bank.bankrest.repository.CardRepository;
import com.bank.bankrest.repository.UserRepository;
import com.bank.bankrest.service.impl.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CardResponse createCard(Long userId, CreateCardRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Card card = new Card();
        card.setOwner(user);
        card.setOwnerName(request.getOwnerName());
        card.setExpiryDate(request.getExpiryDate());
        card.setStatus(CardStatus.ACTIVE);
        card.setBalance(BigDecimal.ZERO);
        card.setEncryptedNumber("GENERATED_NUMBER"); // позже сделаем генератор

        cardRepository.save(card);
        return CardMapper.toResponse(card);
    }

    @Override
    public Page<CardResponse> getUserCards(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return cardRepository.findAllByOwner(user, pageable)
                .map(CardMapper::toResponse);
    }

    @Override
    public void blockCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("Card not found"));
        card.setStatus(CardStatus.BLOCKED);
    }

    @Override
    public void activateCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("Card not found"));
        card.setStatus(CardStatus.ACTIVE);
    }
}

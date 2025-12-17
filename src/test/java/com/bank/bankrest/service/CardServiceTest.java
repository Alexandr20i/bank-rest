package com.bank.bankrest.service;

import com.bank.bankrest.domain.model.Card;
import com.bank.bankrest.domain.enums.CardStatus;
import com.bank.bankrest.repository.CardRepository;
import com.bank.bankrest.repository.UserRepository;
import com.bank.bankrest.service.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CardServiceTest {

    private CardRepository cardRepository;
    private UserRepository userRepository;
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        cardRepository = Mockito.mock(CardRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        cardService = new CardServiceImpl(cardRepository, userRepository);
    }

    @Test
    void blockCard_success() {
        Card card = new Card();
        card.setStatus(CardStatus.ACTIVE);

        when(cardRepository.findById(1L))
                .thenReturn(Optional.of(card));

        cardService.blockCard(1L);

        assertEquals(CardStatus.BLOCKED, card.getStatus());
        verify(cardRepository).save(card);
    }
}

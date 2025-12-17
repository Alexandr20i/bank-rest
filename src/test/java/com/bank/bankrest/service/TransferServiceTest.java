package com.bank.bankrest.service;

import com.bank.bankrest.domain.model.Card;
import com.bank.bankrest.dto.request.transfer.TransferRequest;
import com.bank.bankrest.repository.CardRepository;
import com.bank.bankrest.repository.TransferRepository;
import com.bank.bankrest.service.TransferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransferServiceTest {

    private CardRepository cardRepository;
    private TransferRepository transferRepository;
    private TransferServiceImpl transferService;

    @BeforeEach
    void setUp() {
        cardRepository = Mockito.mock(CardRepository.class);
        transferRepository = Mockito.mock(TransferRepository.class);
        transferService = new TransferServiceImpl(cardRepository, transferRepository);
    }

    @Test
    void transfer_success() {
        Card fromCard = new Card();
        fromCard.setBalance(new BigDecimal("1000"));

        Card toCard = new Card();
        toCard.setBalance(new BigDecimal("500"));

        when(cardRepository.findById(1L)).thenReturn(Optional.of(fromCard));
        when(cardRepository.findById(2L)).thenReturn(Optional.of(toCard));

        TransferRequest request = new TransferRequest();
        request.setFromCardId(1L);
        request.setToCardId(2L);
        request.setAmount(new BigDecimal("200"));

        transferService.transfer(1L, request);

        assertEquals(new BigDecimal("800"), fromCard.getBalance());
        assertEquals(new BigDecimal("700"), toCard.getBalance());
    }

}

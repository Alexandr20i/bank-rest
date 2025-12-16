package com.bank.bankrest.mapper;

import com.bank.bankrest.domain.model.Card;
import com.bank.bankrest.dto.response.card.CardResponse;
import com.bank.bankrest.util.CardNumberMasker;

public class CardMapper {

    private CardMapper() {}

    public static CardResponse toResponse(Card card) {
        CardResponse response = new CardResponse();
        response.setId(card.getId());
        response.setMaskedNumber(
                CardNumberMasker.mask(card.getEncryptedNumber())
        );
        response.setOwnerName(card.getOwnerName());
        response.setExpiryDate(card.getExpiryDate());
        response.setStatus(card.getStatus());
        response.setBalance(card.getBalance());
        return response;
    }
}

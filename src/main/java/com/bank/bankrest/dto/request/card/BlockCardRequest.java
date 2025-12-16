package com.bank.bankrest.dto.request.card;

import jakarta.validation.constraints.NotNull;

public class BlockCardRequest {

    @NotNull
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}

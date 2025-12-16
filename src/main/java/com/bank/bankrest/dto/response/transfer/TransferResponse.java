package com.bank.bankrest.dto.response.transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferResponse {

    private final Long id;
    private final Long fromCardId;
    private final Long toCardId;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;

    public TransferResponse(
            Long id,
            Long fromCardId,
            Long toCardId,
            BigDecimal amount,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.fromCardId = fromCardId;
        this.toCardId = toCardId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getFromCardId() {
        return fromCardId;
    }

    public Long getToCardId() {
        return toCardId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

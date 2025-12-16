package com.bank.bankrest.mapper;

import com.bank.bankrest.domain.model.Transfer;
import com.bank.bankrest.dto.response.transfer.TransferResponse;

public class TransferMapper {

    private TransferMapper() {}

    public static TransferResponse toResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getId(),
                transfer.getFromCard().getId(),
                transfer.getToCard().getId(),
                transfer.getAmount(),
                transfer.getCreatedAt()
        );
    }
}


package com.bank.bankrest.service.impl;

import com.bank.bankrest.dto.request.transfer.TransferRequest;
import com.bank.bankrest.dto.response.transfer.TransferResponse;

public interface TransferService {

    TransferResponse transfer(Long userId, TransferRequest request);
}

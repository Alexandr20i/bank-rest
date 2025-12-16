package com.bank.bankrest.controller;

import com.bank.bankrest.dto.request.transfer.TransferRequest;
import com.bank.bankrest.dto.response.transfer.TransferResponse;
import com.bank.bankrest.security.model.CustomUserDetails;
import com.bank.bankrest.service.impl.TransferService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public TransferResponse transfer(
            @AuthenticationPrincipal CustomUserDetails user,
            @Valid @RequestBody TransferRequest request
    ) {
        return transferService.transfer(user.getId(), request);
    }
}

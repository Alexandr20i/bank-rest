package com.bank.bankrest.service;

import com.bank.bankrest.domain.model.Card;
import com.bank.bankrest.domain.model.Transfer;
import com.bank.bankrest.domain.enums.CardStatus;
import com.bank.bankrest.dto.request.transfer.TransferRequest;
import com.bank.bankrest.dto.response.transfer.TransferResponse;
import com.bank.bankrest.exception.NotFoundException;
import com.bank.bankrest.mapper.TransferMapper;
import com.bank.bankrest.repository.CardRepository;
import com.bank.bankrest.repository.TransferRepository;
import com.bank.bankrest.service.impl.TransferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferServiceImpl implements TransferService {

    private final CardRepository cardRepository;
    private final TransferRepository transferRepository;

    public TransferServiceImpl(CardRepository cardRepository, TransferRepository transferRepository) {
        this.cardRepository = cardRepository;
        this.transferRepository = transferRepository;
    }

    @Override
    @Transactional
    public TransferResponse transfer(Long userId, TransferRequest request) {

        Card from = cardRepository.findById(request.getFromCardId())
                .orElseThrow(() -> new NotFoundException("From card not found"));

        Card to = cardRepository.findById(request.getToCardId())
                .orElseThrow(() -> new NotFoundException("To card not found"));

        if (from.getStatus() != CardStatus.ACTIVE || to.getStatus() != CardStatus.ACTIVE) {
            throw new NotFoundException("Card is not active");
        }

        if (from.getBalance().compareTo(request.getAmount()) < 0) {
            throw new NotFoundException("Insufficient funds");
        }

        from.setBalance(from.getBalance().subtract(request.getAmount()));
        to.setBalance(to.getBalance().add(request.getAmount()));

        Transfer transfer = new Transfer();
        transfer.setFromCard(from);
        transfer.setToCard(to);
        transfer.setAmount(request.getAmount());

        transferRepository.save(transfer);
        return TransferMapper.toResponse(transfer);
    }
}

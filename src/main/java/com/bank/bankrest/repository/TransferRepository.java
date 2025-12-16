package com.bank.bankrest.repository;

import com.bank.bankrest.domain.model.Transfer;
import com.bank.bankrest.domain.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findAllByFromCardOrToCard(Card from, Card to);
}

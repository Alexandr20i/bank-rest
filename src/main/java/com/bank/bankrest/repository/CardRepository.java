package com.bank.bankrest.repository;

import com.bank.bankrest.domain.model.Card;
import com.bank.bankrest.domain.model.User;
import com.bank.bankrest.domain.enums.CardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    Page<Card> findAllByOwner(User owner, Pageable pageable);

    List<Card> findAllByOwnerAndStatus(User owner, CardStatus status);

    boolean existsByEncryptedNumber(String encryptedNumber);
}

package org.example.sberpractice.repository;

import org.example.sberpractice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
    List<Card> findByActiveTrueAndExpiryDateBefore(LocalDate date);
}
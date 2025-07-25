package org.example.sberpractice.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.sberpractice.model.Card;
import org.example.sberpractice.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CardExpiryService {

    private final CardRepository cardRepository;

    public CardExpiryService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PreDestroy
    public void logExpiredCardsOnShutdown() {
        List<Card> expiredCards = cardRepository.findAll().stream()
                .filter(Card::getActive) // только активные
                .filter(card -> card.getExpiryDate().isBefore(LocalDate.now()))
                .toList();

        if (!expiredCards.isEmpty()) {
            try (FileWriter writer = new FileWriter("logs/expired_cards.log", true)) {
                for (Card card : expiredCards) {
                    writer.write("Карта №" + card.getCardNumber() + " истекла: " + card.getExpiryDate() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

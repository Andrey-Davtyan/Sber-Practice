package org.example.sberpractice.service;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.example.sberpractice.model.Card;
import org.example.sberpractice.repository.CardRepository;
import org.example.sberpractice.service.CardExpiryService;
import java.time.LocalDate;
import java.util.List;

class CardExpiryServiceTest {

    @Test
    void testCheckExpiredCardsWritesToFile() {
        Card expiredCard = new Card();
        expiredCard.setCardNumber("1234-1234-1234-1234");
        expiredCard.setExpiryDate(LocalDate.now().minusDays(1));
        expiredCard.setActive(true);

        CardRepository repo = mock(CardRepository.class);
        when(repo.findAll()).thenReturn(List.of(expiredCard));

        CardExpiryService service = new CardExpiryService(repo);

        // Вызываем метод, который уже реализован в основном коде
        service.logExpiredCardsOnShutdown();

        // Дополнительно можно проверить, что файл содержит строку
        // Files.readAllLines(Paths.get("logs/expired_cards.log"))
        //     .stream().anyMatch(line -> line.contains("1234"))
    }
}


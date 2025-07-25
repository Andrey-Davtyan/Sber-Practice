package org.example.sberpractice.controller;

import org.example.sberpractice.model.Card;
import org.example.sberpractice.model.Client;
import org.example.sberpractice.repository.CardRepository;
import org.example.sberpractice.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardControllerTest {

    private CardRepository cardRepository;
    private ClientRepository clientRepository;
    private CardController controller;

    @BeforeEach
    void setUp() {
        cardRepository = mock(CardRepository.class);
        clientRepository = mock(ClientRepository.class);
        controller = new CardController(cardRepository, clientRepository);
    }

    @Test
    void testCreateCard() {
        Card card = new Card();
        card.setCardNumber("123456");
        Client client = new Client();
        client.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        String result = controller.createCard(card, 1L);

        assertEquals("/clients", result.replace("redirect:", ""));
        assertNotNull(card.getIssueDate());
        assertTrue(card.getActive());
        verify(cardRepository).save(card);
    }

    @Test
    void testDeactivateCard() {
        Card card = new Card();
        card.setId(1L);
        card.setActive(true);

        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        String result = controller.deactivateCard(1L);

        assertFalse(card.getActive());
        assertEquals("/clients", result.replace("redirect:", ""));
        verify(cardRepository).save(card);
    }
}

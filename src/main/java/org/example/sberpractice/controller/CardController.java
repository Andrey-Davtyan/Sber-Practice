package org.example.sberpractice.controller;

import org.example.sberpractice.model.Card;
import org.example.sberpractice.model.Client;
import org.example.sberpractice.repository.CardRepository;
import org.example.sberpractice.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/cards")
public class CardController {

    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;

    public CardController(CardRepository cardRepository, ClientRepository clientRepository) {
        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("card", new Card());
        model.addAttribute("clients", clientRepository.findAll());
        return "add-card";
    }

    @PostMapping
    public String createCard(@ModelAttribute Card card, @RequestParam Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        card.setClient(client);
        if (card.getIssueDate() == null) {
            card.setIssueDate(LocalDate.now());
        }
        if (card.getExpiryDate() == null) {
            card.setExpiryDate(card.getIssueDate().plusYears(3));
        }
        card.setActive(true);
        cardRepository.save(card);
        return "redirect:/clients";
    }

    @PostMapping("/{id}/deactivate")
    public String deactivateCard(@PathVariable Long id) {
        cardRepository.findById(id).ifPresent(card -> {
            card.setActive(false);
            cardRepository.save(card);
        });
        return "redirect:/clients";
    }


}

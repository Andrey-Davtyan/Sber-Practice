package org.example.sberpractice.controller;

import org.example.sberpractice.model.Client;
import org.example.sberpractice.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String getAllClients(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("client", new Client());
        return "clients";
    }

    @PostMapping
    public String createClient(@ModelAttribute Client client) {
        clientRepository.findByFullNameAndBirthDate(client.getFullName(), client.getBirthDate()).ifPresentOrElse(
                        c -> {}, // уже есть — ничего не делаем
                        () -> clientRepository.save(client)
                );
        return "redirect:/clients";
    }
}

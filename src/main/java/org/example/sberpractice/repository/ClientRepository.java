package org.example.sberpractice.repository;

import org.example.sberpractice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByFullNameAndBirthDate(String fullName, LocalDate birthDate);
}
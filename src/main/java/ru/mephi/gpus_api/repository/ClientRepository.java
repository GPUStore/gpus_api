package ru.mephi.gpus_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.gpus_api.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findClientByEmail(String email);
}

package ru.mephi.gpus_api.repository.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mephi.gpus_api.entity.clients.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findClientByEmailOrNickname(String email, String nickname);
}
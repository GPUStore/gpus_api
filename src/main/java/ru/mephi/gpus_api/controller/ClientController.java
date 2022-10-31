package ru.mephi.gpus_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;
import ru.mephi.gpus_api.service.ClientService;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody ClientDTO clientDTO) {
        return clientService.createOrUpdateClient(clientDTO);
    }
}

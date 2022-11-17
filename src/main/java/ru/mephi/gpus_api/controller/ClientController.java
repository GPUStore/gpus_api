package ru.mephi.gpus_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;
import ru.mephi.gpus_api.service.ClientService;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/unsubscribe")
    public Boolean unsubscribe(@RequestBody ClientDTO unsub) {
        return clientService.unsubscribe(unsub);
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody ClientDTO clientDTO) {
        return clientService.createOrUpdateClient(clientDTO);
    }
}

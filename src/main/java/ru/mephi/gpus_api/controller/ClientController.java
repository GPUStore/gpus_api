package ru.mephi.gpus_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mephi.gpus_api.entity.clients.ClientUnsubDto;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;
import ru.mephi.gpus_api.service.ClientService;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/unsubscribe")
    public Boolean unsubscribe(@RequestBody ClientUnsubDto unsub) {
        return clientService.unsubscribe(unsub);
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody ClientDTO clientDTO) {
        return clientService.createOrUpdateClient(clientDTO);
    }
}

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

    @GetMapping("/unsubscribe/clientId={clientId}&productId={productId}")
    public Boolean unsubscribe(@PathVariable String clientId, @PathVariable String productId) {
        return clientService.unsubscribe(clientId, productId);
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody ClientDTO clientDTO) {
        return clientService.createOrUpdateClient(clientDTO);
    }
}

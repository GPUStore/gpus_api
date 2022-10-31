package ru.mephi.gpus_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.gpus_api.entity.clients.Client;
import ru.mephi.gpus_api.entity.clients.ProductLink;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;
import ru.mephi.gpus_api.repository.clients.ClientRepository;
import ru.mephi.gpus_api.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public String createOrUpdateClient(ClientDTO clientDTO) {
        Validator.validate(clientDTO);
        String productId = clientDTO.getProductId();
        String email = clientDTO.getEmail();
        Client client = clientRepository.findClientByEmail(email).orElse(createClient(email, new ArrayList<>()));
        ProductLink productLink = createProductLink(productId, client);
        addIfNotContains(client, productLink);
        return clientRepository.save(client).getId();
    }

    private Client createClient(String email, List<ProductLink> productLinks) {
        return new Client()
                .setEmail(email)
                .setProductIds(productLinks);
    }

    private ProductLink createProductLink(String productId, Client client) {
        return new ProductLink()
                .setProductId(productId)
                .setClient(client);
    }

    private void addIfNotContains(Client client, ProductLink productLink) {
        if (!client.getProductIds().contains(productLink)) {
            client.getProductIds().add(productLink);
        }
    }

}

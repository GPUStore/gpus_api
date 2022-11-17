package ru.mephi.gpus_api.service;

import org.junit.jupiter.api.Test;
import ru.mephi.gpus_api.AbstractAppTest;
import ru.mephi.gpus_api.ClientUtils;
import ru.mephi.gpus_api.entity.clients.Client;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;
import ru.mephi.gpus_api.exception.ClientWithEmailNotFoundException;
import ru.mephi.gpus_api.exception.MissingPropertyException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mephi.gpus_api.ProductLinkUtils.isContains;

class ClientServiceTest extends AbstractAppTest {
    @Test
    void createOrUpdateClientTestWithCorrectClientDtoTest() {
        ClientDTO expected = ClientUtils.createClientDto();

        String clientId = clientService.createOrUpdateClient(expected);
        Client actual = clientRepository.findById(clientId).get();

        assertEquals(32, clientId.length());
        assertEquals(expected.getEmail(), actual.getEmail());

        assertTrue(isContains(actual.getProductIds(), expected.getProductId()));
    }

    @Test
    void createOrUpdateClientTestWithCorrectClientDtoOtherLinkTest() {
        ClientDTO clientDto1 = ClientUtils.createClientDto()
                .setProductId("1");
        ClientDTO clientDto2 = ClientUtils.createClientDto()
                .setProductId("2");

        String clientId1 = clientService.createOrUpdateClient(clientDto1);
        String clientId2 = clientService.createOrUpdateClient(clientDto2);
        Client actual = clientRepository.findById(clientId2).get();

        assertEquals(32, clientId1.length());
        assertEquals(clientId1, clientId2);
        assertEquals(clientDto1.getEmail(), actual.getEmail());
        assertTrue(isContains(actual.getProductIds(), clientDto1.getProductId()));

    }

    @Test
    void createOrUpdateClientTestWithIncorrectClientDtoTest() {
        ClientDTO clientDto = ClientUtils.createClientDto()
                .setEmail("");

        assertThrows(MissingPropertyException.class, () -> clientService.createOrUpdateClient(clientDto));
    }

    @Test
    void unsubscribeAllTest() {
        ClientDTO clientDto = ClientUtils.createClientDto();
        String clientId = clientService.createOrUpdateClient(clientDto);
        clientDto.setProductId(null);

        boolean isUnsubscribe = clientService.unsubscribe(clientDto);

        assertTrue(isUnsubscribe);
        assertEquals(Optional.empty(), clientRepository.findById(clientId));
    }

    @Test
    void unsubscribeWithIncorrectClientTest() {
        ClientDTO clientDto = ClientUtils.createClientDto();
        clientService.createOrUpdateClient(clientDto);
        clientDto.setEmail("otherEmail");

        assertThrows(ClientWithEmailNotFoundException.class, () -> clientService.unsubscribe(clientDto));
    }

    @Test
    void unsubscribeTest() {
        ClientDTO clientDto = ClientUtils.createClientDto();
        String clientId = clientService.createOrUpdateClient(clientDto);

        boolean isUnsubscribe = clientService.unsubscribe(clientDto);
        Client client = clientRepository.findById(clientId).get();

        assertTrue(isUnsubscribe);
        assertFalse(isContains(client.getProductIds(), clientDto.getProductId()));
    }

    @Test
    void unsubscribeWithNotExistsProductIdTest() {
        ClientDTO clientDto = ClientUtils.createClientDto();
        clientService.createOrUpdateClient(clientDto);
        clientDto.setProductId("NotExistsProductId");

        boolean isUnsubscribe = clientService.unsubscribe(clientDto);

        assertFalse(isUnsubscribe);
    }
}
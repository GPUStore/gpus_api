package ru.mephi.gpus_api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mephi.gpus_api.AbstractAppTest;
import ru.mephi.gpus_api.ClientUtils;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;
import ru.mephi.gpus_api.exception.MissingPropertyException;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest extends AbstractAppTest {

    @Autowired
    private ClientService clientService;

    @Test
    void createOrUpdateClientTestWithCorrectClientDtoTest() {
        ClientDTO clientDto = ClientUtils.createClientDto();

        String clientId = clientService.createOrUpdateClient(clientDto);

        assertEquals(32, clientId.length());
    }

    @Test
    void createOrUpdateClientTestWithCorrectClientDtoOtherLinkTest() {
        ClientDTO clientDto1 = ClientUtils.createClientDto()
                .setProductId("1");
        ClientDTO clientDto2 = ClientUtils.createClientDto()
                .setProductId("2");
        clientService.createOrUpdateClient(clientDto1);

        String clientId = clientService.createOrUpdateClient(clientDto2);

        assertEquals(32, clientId.length());
    }

    @Test
    void createOrUpdateClientTestWithIncorrectClientDtoTest() {
        ClientDTO clientDto = ClientUtils.createClientDto()
                .setNickname(null)
                .setEmail("");

        assertThrows(MissingPropertyException.class, () -> clientService.createOrUpdateClient(clientDto));
    }
}
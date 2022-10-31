package ru.mephi.gpus_api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mephi.gpus_api.AbstractAppTest;
import ru.mephi.gpus_api.ClientUtils;
import ru.mephi.gpus_api.entity.dto.ClientDTO;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest extends AbstractAppTest {

    @Autowired
    private ClientService clientService;

    @Test
    void createOrUpdateClientTestWithCorrectClientDto() {
        ClientDTO clientDto = ClientUtils.createClientDto();

        String clientId = clientService.createOrUpdateClient(clientDto);

        assertEquals(32, clientId.length());
    }
}
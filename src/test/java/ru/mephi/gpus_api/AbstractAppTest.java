package ru.mephi.gpus_api;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mephi.gpus_api.mapper.StoreMapper;
import ru.mephi.gpus_api.repository.clients.ClientRepository;
import ru.mephi.gpus_api.service.ClientService;

@SpringBootTest
public class AbstractAppTest {
    @Autowired
    protected StoreMapper storeMapper;
    @Autowired
    protected ClientService clientService;
    @Autowired
    protected ClientRepository clientRepository;

    @AfterEach
    void clearAll() {
        clientRepository.deleteAll();
    }
}

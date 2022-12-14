package ru.mephi.gpus_api;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.gpus_api.mapper.ProductMapper;
import ru.mephi.gpus_api.mapper.StoreMapper;
import ru.mephi.gpus_api.repository.clients.ClientRepository;
import ru.mephi.gpus_api.service.ClientService;
import ru.mephi.gpus_api.service.HibernateSearchService;

@SpringBootTest
public class AbstractAppTest {

    @Autowired
    protected StoreMapper storeMapper;
    @Autowired
    protected ClientService clientService;
    @Autowired
    protected ClientRepository clientRepository;
    @Autowired
    protected HibernateSearchService hibernateSearchService;
    @Autowired
    protected ProductMapper productMapper;

    @AfterEach
    @Transactional
    void clearAll() {
        clientRepository.deleteAll();
    }
}

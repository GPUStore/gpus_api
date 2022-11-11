package ru.mephi.gpus_api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mephi.gpus_api.mapper.StoreMapper;
import ru.mephi.gpus_api.repository.clients.ClientRepository;

@SpringBootTest
public class AbstractAppTest {

    @Autowired
    protected  StoreMapper storeMapper;

    @Autowired
    protected ClientRepository clientRepository;

    @BeforeEach
    void clearAll(){
        clientRepository.deleteAll();
    }

}

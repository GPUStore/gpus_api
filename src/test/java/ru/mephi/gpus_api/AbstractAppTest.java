package ru.mephi.gpus_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mephi.gpus_api.mapper.StoreMapper;

@SpringBootTest
public class AbstractAppTest {

    @Autowired
    protected  StoreMapper storeMapper;

}

package ru.mephi.gpus_api;

import ru.mephi.gpus_api.entity.dto.ClientDTO;

public class ClientUtils {

    public static ClientDTO createClientDto(){
        return new ClientDTO()
                .setEmail("teste@mail.ru")
                .setProductId("1");
    }

}

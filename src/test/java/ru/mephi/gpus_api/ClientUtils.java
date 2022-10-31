package ru.mephi.gpus_api;

import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;

public class ClientUtils {

    public static ClientDTO createClientDto(){
        return new ClientDTO()
                .setEmail("test@mail.ru")
                .setNickname("test-nick")
                .setProductId("1");
    }

}

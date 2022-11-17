package ru.mephi.gpus_api;

import ru.mephi.gpus_api.entity.clients.Client;
import ru.mephi.gpus_api.entity.clients.ProductLink;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mephi.gpus_api.ProductLinkUtils.assertProductLinks;

public class ClientUtils {

    public static ClientDTO createClientDto() {
        return new ClientDTO()
                .setEmail("test@mail.ru")
                .setProductId("1");
    }

    public static void assertClients(Client expected, Client actual) {
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getProductIds().size(), actual.getProductIds().size());
        for (int i = 0; i < expected.getProductIds().size(); i++) {
            ProductLink exp = expected.getProductIds().get(i);
            ProductLink act = actual.getProductIds().get(i);
            assertProductLinks(exp, act);
        }
    }
}

package ru.mephi.gpus_api.entity.clients.dto;

import lombok.Getter;

@Getter
public class ClientDTO {

    String email;
    String productId;

    public ClientDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientDTO setProductId(String productId) {
        this.productId = productId;
        return this;
    }
}

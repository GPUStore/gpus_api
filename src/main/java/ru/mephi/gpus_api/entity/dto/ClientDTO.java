package ru.mephi.gpus_api.entity.dto;

import lombok.Data;

@Data
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

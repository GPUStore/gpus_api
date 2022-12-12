package ru.mephi.gpus_api.entity.products.dto.store;

import lombok.Getter;

@Getter
public class StoreRsDto {

    private String name;
    private String url;
    private Double cost;

    public StoreRsDto setName(String name) {
        this.name = name;
        return this;
    }

    public StoreRsDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public StoreRsDto setCost(Double cost) {
        this.cost = cost;
        return this;
    }
}

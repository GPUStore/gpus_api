package ru.mephi.gpus_api.entity.products.dto;

import lombok.Getter;

@Getter
public class StoreRqDto {
    private String name;
    private String url;
    private Double cost;

    public StoreRqDto setName(String name) {
        this.name = name;
        return this;
    }

    public StoreRqDto setUrl(String url) {
        this.url = url;
        return this;
    }

    public StoreRqDto setCost(Double cost) {
        this.cost = cost;
        return this;
    }
}

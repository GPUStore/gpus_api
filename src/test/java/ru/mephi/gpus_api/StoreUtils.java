package ru.mephi.gpus_api;

import ru.mephi.gpus_api.entity.products.Store;
import ru.mephi.gpus_api.entity.products.dto.StoreRqDto;

public class StoreUtils {

    public static Store createCorrectStore() {
        return new Store()
                .setProduct(null)
                .setCost(1.0)
                .setId("1")
                .setName("testName")
                .setUrl("testUrl");
    }

    public static StoreRqDto createCorrectStoreDto() {
        return new StoreRqDto()
                .setCost(1.0)
                .setName("testName")
                .setUrl("testUrl");
    }

}

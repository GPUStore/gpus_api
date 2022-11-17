package ru.mephi.gpus_api.exception;

import ru.mephi.gpus_api.exception.enumiration.ErrorMessage;

public class ProductWithIdNotFoundException extends GpuStoreException {

    public ProductWithIdNotFoundException(String id) {
        super(ErrorMessage.PRODUCT_NOT_FOUND, id);
    }
}

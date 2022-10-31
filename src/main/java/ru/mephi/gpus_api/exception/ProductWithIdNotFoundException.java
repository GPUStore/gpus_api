package ru.mephi.gpus_api.exception;

public class ProductWithIdNotFoundException extends GpuStoreException {

    public ProductWithIdNotFoundException(Long id) {
        super(ErrorMessage.PRODUCT_NOT_FOUND, Long.toString(id));
    }
}

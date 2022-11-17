package ru.mephi.gpus_api.exception;

import ru.mephi.gpus_api.exception.enumiration.ErrorMessage;

public class StoreExistsException extends GpuStoreException {
    public StoreExistsException(String url) {
        super(ErrorMessage.STORE_EXISTS, url);
    }
}
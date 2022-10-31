package ru.mephi.gpus_api.exception;

import ru.mephi.gpus_api.exception.enumiration.ErrorMessage;

public class ClientWithIdNotFoundException extends GpuStoreException {

    public ClientWithIdNotFoundException(String id) {
        super(ErrorMessage.CLIENT_NOT_FOUND, id);
    }
}

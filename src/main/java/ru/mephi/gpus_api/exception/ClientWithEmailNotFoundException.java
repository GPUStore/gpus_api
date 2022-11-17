package ru.mephi.gpus_api.exception;

import ru.mephi.gpus_api.exception.enumiration.ErrorMessage;

public class ClientWithEmailNotFoundException extends GpuStoreException {

    public ClientWithEmailNotFoundException(String email) {
        super(ErrorMessage.CLIENT_WITH_EMAIl_NOT_FOUND, email);
    }
}

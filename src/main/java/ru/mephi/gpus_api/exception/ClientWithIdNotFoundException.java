package ru.mephi.gpus_api.exception;

public class ClientWithIdNotFoundException extends GpuStoreException {

    public ClientWithIdNotFoundException(Long id) {
        super(ErrorMessage.CLIENT_NOT_FOUND, Long.toString(id));
    }
}

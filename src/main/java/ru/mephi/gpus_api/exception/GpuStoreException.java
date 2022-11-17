package ru.mephi.gpus_api.exception;

import ru.mephi.gpus_api.exception.enumiration.ErrorMessage;

public abstract class GpuStoreException extends RuntimeException {

    protected GpuStoreException(ErrorMessage error, String attribute) {
        super(String.format(error.getMessage(), attribute));
    }
}

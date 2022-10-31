package ru.mephi.gpus_api.exception;

public abstract class GpuStoreException extends RuntimeException {

    protected GpuStoreException(ErrorMessage error, String attribute) {
        super(String.format(error.getMessage(), attribute));
    }
}

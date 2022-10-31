package ru.mephi.gpus_api.exception;

public class MissingPropertyException extends GpuStoreException {

    public MissingPropertyException(String property) {
        super(ErrorMessage.MISSING_PROPERTY, property);
    }
}

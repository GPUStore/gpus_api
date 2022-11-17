package ru.mephi.gpus_api.exception;

import ru.mephi.gpus_api.exception.enumiration.ErrorMessage;

public class MissingPropertyException extends GpuStoreException {
    public MissingPropertyException(String property) {
        super(ErrorMessage.MISSING_PROPERTY, property);
    }
}

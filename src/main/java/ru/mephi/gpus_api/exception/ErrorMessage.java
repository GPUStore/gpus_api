package ru.mephi.gpus_api.exception;

public enum ErrorMessage {

    PRODUCT_NOT_FOUND("011", "Product with id=%s not found."),
    CLIENT_NOT_FOUND("021", "Client with id=%s not found."),
    MISSING_PROPERTY("031", "Property=%s missing or equals null."),

    TECHNICAL_ERROR("001", "A technical error has occurred."),
    VALIDATION_ERROR("002", "Request validation error.");

    private final String code;
    private final String message;

    ErrorMessage(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "{" +
                "code: '" + code + '\'' +
                ", message: '" + message + '\'' +
                '}';
    }
}

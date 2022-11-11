package ru.mephi.gpus_api.exception.enumiration;

public enum ErrorMessage {

    PRODUCT_NOT_FOUND("011", "Product with id=%s not found."),
    CLIENT_WITH_ID_NOT_FOUND("021", "Client with id=%s not found."),
    CLIENT_WITH_EMAIl_NOT_FOUND("021", "Client with email=%s not found."),
    STORE_EXISTS("041", "This store already exists with url=%s."),
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

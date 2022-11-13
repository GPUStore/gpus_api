package ru.mephi.gpus_api.validation;

import org.junit.jupiter.api.Test;
import ru.mephi.gpus_api.ClientUtils;
import ru.mephi.gpus_api.entity.clients.dto.ClientDTO;
import ru.mephi.gpus_api.exception.MissingPropertyException;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateCorrectDtoTest() {
        ClientDTO dto = ClientUtils.createClientDto();

        assertDoesNotThrow(() -> Validator.validate(dto));
    }

    @Test
    void validateCorrectDtoWithNickTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setEmail(null);

        assertDoesNotThrow(() -> Validator.validate(dto));
    }

    @Test
    void validateWithEmptyEmailAndNickTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setEmail("");

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithNullProductIdTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setProductId(null);

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithEmptyProductIdTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setProductId("");

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }
}
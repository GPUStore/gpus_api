package ru.mephi.gpus_api.validation;

import org.junit.jupiter.api.Test;
import ru.mephi.gpus_api.ClientUtils;
import ru.mephi.gpus_api.entity.dto.ClientDTO;
import ru.mephi.gpus_api.exception.MissingPropertyException;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateCorrectDtoTest() {
        ClientDTO dto = ClientUtils.createClientDto();

        assertDoesNotThrow(() -> Validator.validate(dto));
    }

    @Test
    void validateWithNullProductIdTest() {
        ClientDTO dto = ClientUtils.createClientDto();
        dto.setProductId(null);

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithNullEmailTest() {
        ClientDTO dto = ClientUtils.createClientDto();
        dto.setEmail(null);

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithEmptyProductIdTest() {
        ClientDTO dto = ClientUtils.createClientDto();
        dto.setProductId("");

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithEmptyEmailTest() {
        ClientDTO dto = ClientUtils.createClientDto();
        dto.setEmail("");

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }
}
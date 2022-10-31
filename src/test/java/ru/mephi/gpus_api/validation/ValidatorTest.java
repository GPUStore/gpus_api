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
    void validateCorrectDtoWithEmailTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setNickname(null);

        assertDoesNotThrow(() -> Validator.validate(dto));
    }

    @Test
    void validateCorrectDtoWithNickTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setEmail(null);

        assertDoesNotThrow(() -> Validator.validate(dto));
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

    @Test
    void validateWithNullEmailAndNickTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setEmail(null)
                .setNickname(null);

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithEmptyEmailAndNickTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setEmail("")
                .setNickname("");

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithNullEmailAndEmptyNickTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setEmail(null)
                .setNickname("");

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }

    @Test
    void validateWithEmptyEmailAndNullNickTest() {
        ClientDTO dto = ClientUtils.createClientDto()
                .setEmail("")
                .setNickname(null);

        assertThrows(MissingPropertyException.class, () -> Validator.validate(dto));
    }
}
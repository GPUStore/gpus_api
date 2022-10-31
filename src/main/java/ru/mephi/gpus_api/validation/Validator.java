package ru.mephi.gpus_api.validation;

import ru.mephi.gpus_api.entity.dto.ClientDTO;
import ru.mephi.gpus_api.exception.MissingPropertyException;

public class Validator {

    public static void validate(ClientDTO dto){
        if(dto.getEmail() == null || dto.getEmail().equals("")){
            throw new MissingPropertyException("email");
        }
        if(dto.getProductId() == null || dto.getProductId().equals("")){
            throw new MissingPropertyException("product id");
        }
    }

}

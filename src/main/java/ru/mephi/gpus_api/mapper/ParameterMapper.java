package ru.mephi.gpus_api.mapper;

import org.mapstruct.Mapper;
import ru.mephi.gpus_api.entity.products.Parameter;
import ru.mephi.gpus_api.entity.products.dto.parameter.ParameterRsDto;

@Mapper(componentModel = "spring")
public interface ParameterMapper {

    ParameterRsDto entityToDto(Parameter entity);
}

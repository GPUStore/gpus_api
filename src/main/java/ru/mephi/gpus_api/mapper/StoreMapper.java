package ru.mephi.gpus_api.mapper;

import org.mapstruct.Mapper;
import ru.mephi.gpus_api.entity.products.Store;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRqDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRsDto;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreRsDto entityToDto(Store entity);
    Store dtoToEntity(StoreRqDto dto);
}

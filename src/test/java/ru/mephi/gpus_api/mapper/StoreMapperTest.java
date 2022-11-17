package ru.mephi.gpus_api.mapper;

import org.junit.jupiter.api.Test;
import ru.mephi.gpus_api.AbstractAppTest;
import ru.mephi.gpus_api.entity.products.Store;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRqDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRsDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mephi.gpus_api.StoreUtils.createCorrectStore;
import static ru.mephi.gpus_api.StoreUtils.createCorrectStoreDto;

class StoreMapperTest extends AbstractAppTest {

    @Test
    void entityToDtoTest() {
        Store entity = createCorrectStore();

        StoreRsDto dto = storeMapper.entityToDto(entity);

        assertEquals(entity.getUrl(), dto.getUrl());
        assertEquals(entity.getCost(), dto.getCost());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    void dtoToEntityTest() {
        StoreRqDto dto = createCorrectStoreDto();

        Store entity = storeMapper.dtoToEntity(dto);

        assertEquals(dto.getUrl(), entity.getUrl());
        assertEquals(dto.getCost(), entity.getCost());
        assertEquals(dto.getName(), entity.getName());
    }
}
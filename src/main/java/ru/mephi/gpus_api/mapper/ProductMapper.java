package ru.mephi.gpus_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;

@Mapper(componentModel = "spring", uses = {StoreMapper.class, ParameterMapper.class, CategoryMapper.class})
public interface ProductMapper {

    ProductRsDto entityToDto(Product entity);
}

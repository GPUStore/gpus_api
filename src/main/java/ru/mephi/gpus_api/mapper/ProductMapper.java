package ru.mephi.gpus_api.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class ProductMapper {

    StoreMapper storeMapper;
    ParameterMapper parameterMapper;
    CategoryMapper categoryMapper;

    @Mappings({
            @Mapping(target = "stores", expression = "java(entity.getStores().stream().map(storeMapper::entityToDto).toList())"),
            @Mapping(target = "parameters", expression = "java(entity.getParameters().stream().map(parameterMapper::entityToDto).toList())"),
            @Mapping(target = "categories", expression = "java(entity.getCategories().stream().map(categoryMapper::entityToDto).toList())")
    })
    public abstract ProductRsDto entityToDto(Product entity);
}

package ru.mephi.gpus_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;

@Mapper(componentModel = "spring", uses = {StoreMapper.class, ParameterMapper.class, CategoryMapper.class})
public interface ProductMapper {

    ProductRsDto entityToDto(Product entity);

    @Mappings({
            @Mapping(target = "stores", expression = "java(null)"),
            @Mapping(target = "categories", expression = "java(null)")
    })
    ProductRsDto entityToDtoWithParameters(Product entity);
}

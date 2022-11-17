package ru.mephi.gpus_api.mapper;

import org.mapstruct.Mapper;
import ru.mephi.gpus_api.entity.products.Category;
import ru.mephi.gpus_api.entity.products.dto.category.CategoryRsDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryRsDto entityToDto(Category entity);

}

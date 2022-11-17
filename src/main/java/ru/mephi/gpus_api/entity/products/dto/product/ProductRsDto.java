package ru.mephi.gpus_api.entity.products.dto.product;

import lombok.Getter;
import lombok.Setter;
import ru.mephi.gpus_api.entity.products.Type;
import ru.mephi.gpus_api.entity.products.dto.category.CategoryRsDto;
import ru.mephi.gpus_api.entity.products.dto.parameter.ParameterRsDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRsDto;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class ProductRsDto {
    private String name;
    private List<StoreRsDto> stores;
    private String country;
    private double weight;
    private double weightWithBox;
    private List<ParameterRsDto> parameters;
    private Type type;
    private List<CategoryRsDto> categories;
}

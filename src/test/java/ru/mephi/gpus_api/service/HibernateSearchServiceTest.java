package ru.mephi.gpus_api.service;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.mephi.gpus_api.AbstractAppTest;
import ru.mephi.gpus_api.ProductUtils;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateSearchServiceTest extends AbstractAppTest {

    @Test
    void getAllProductPageTest() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ProductRsDto> expected = ProductUtils.getTestProducts().stream()
                .map(productMapper::entityToDtoWithParameters)
                .collect(Collectors.toList());

        List<ProductRsDto> actual = hibernateSearchService.getAllProductPage(pageable);

        assertEquals(expected.size(), actual.size());
        ProductUtils.assertProductsRsDtos(expected.get(0), actual.get(0));
        ProductUtils.assertProductsRsDtos(expected.get(1), actual.get(1));
    }

    @Test
    void getAllProductPageTest2() {
        Pageable pageable = PageRequest.of(1, 10);

        List<ProductRsDto> actual = hibernateSearchService.getAllProductPage(pageable);

        assertEquals(0, actual.size());
    }

    @Test
    void searchProductTest() {
        String textForSearch1 = "1";
        String textForSearch2 = "prod";
        String textForSearch3 = "PRsUCT";
        String textForSearch4 = "PROD1";
        List<ProductRsDto> expected1 = null;
        List<ProductRsDto> expected2 = ProductUtils.getTestProducts().stream()
                .map(productMapper::entityToDtoWithParameters)
                .collect(Collectors.toList());
        List<ProductRsDto> expected3 = ProductUtils.getTestProducts().stream()
                .map(productMapper::entityToDtoWithParameters)
                .collect(Collectors.toList());
        List<ProductRsDto> expected4 = ProductUtils.getTestProducts().stream()
                .map(productMapper::entityToDtoWithParameters)
                .collect(Collectors.toList());


        List<ProductRsDto> actual1 = hibernateSearchService.searchProduct(textForSearch1);
        List<ProductRsDto> actual2 = hibernateSearchService.searchProduct(textForSearch2);
        List<ProductRsDto> actual3 = hibernateSearchService.searchProduct(textForSearch3);
        List<ProductRsDto> actual4 = hibernateSearchService.searchProduct(textForSearch4);

        assertEquals(expected1, actual1);
        ProductUtils.assertLists(
                expected2,
                actual2,
                ProductUtils::assertProductsRsDtos,
                Comparator.comparing(ProductRsDto::getName));
        ProductUtils.assertLists(
                expected3,
                actual3,
                ProductUtils::assertProductsRsDtos,
                Comparator.comparing(ProductRsDto::getName));
        ProductUtils.assertLists(
                expected4,
                actual4,
                ProductUtils::assertProductsRsDtos,
                Comparator.comparing(ProductRsDto::getName));
    }
}

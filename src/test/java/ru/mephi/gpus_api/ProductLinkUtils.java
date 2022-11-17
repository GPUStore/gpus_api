package ru.mephi.gpus_api;

import ru.mephi.gpus_api.entity.clients.ProductLink;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductLinkUtils {
    public static void assertProductLinks(ProductLink expected, ProductLink actual) {
        assertEquals(expected.getProductId(), actual.getProductId());
    }

    public static boolean isContains(List<ProductLink> list, String productId) {
        for (ProductLink link : list) {
            if (link.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
}

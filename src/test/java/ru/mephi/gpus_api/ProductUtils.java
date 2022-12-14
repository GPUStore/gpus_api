package ru.mephi.gpus_api;

import ru.mephi.gpus_api.entity.products.*;
import ru.mephi.gpus_api.entity.products.dto.parameter.ParameterRsDto;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRsDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductUtils {

    public static List<Product> getTestProducts() {
        return List.of(
                createProduct(1)
                        .setStores(of(createStore(1)))
                        .setParameters(of(
                                createParameter(1)
                                        .setCharacteristic(createCharacteristic(1)),
                                createParameter(2)
                                        .setCharacteristic(createCharacteristic(1)),
                                createParameter(3)
                                        .setCharacteristic(createCharacteristic(2)),
                                createParameter(4)
                                        .setCharacteristic(createCharacteristic(2)))),
                createProduct(2)
                        .setStores(of(createStore(2)))
                        .setParameters(of(
                                createParameter(1)
                                        .setCharacteristic(createCharacteristic(1)),
                                createParameter(2)
                                        .setCharacteristic(createCharacteristic(1)),
                                createParameter(3)
                                        .setCharacteristic(createCharacteristic(2)),
                                createParameter(4)
                                        .setCharacteristic(createCharacteristic(2))))
        );
    }


    public static Product createProduct(int number) {
        return new Product()
                .setType(Type.VIDEOCARD)
                .setName("PRODUCT" + number)
                .setCountry("country" + number)
                .setWeight(number)
                .setWeightWithBox(number)
                ;
    }

    protected static Store createStore(int number) {
        return new Store()
                .setName("store" + number)
                .setCost(1.0)
                .setUrl("url" + number)
                ;
    }

    protected static Parameter createParameter(int number) {
        return new Parameter()
                .setName("name" + number)
                .setValue("value" + number)
                ;
    }

    protected static Characteristic createCharacteristic(int number) {
        return new Characteristic().setName("characteristic" + number);
    }

    public static <T> List<T> of(T... entities) {
        return new ArrayList<>(Arrays.asList(entities));
    }

    public static void assertProductsRsDtos(ProductRsDto expected, ProductRsDto actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getWeight(), actual.getWeight());
        assertEquals(expected.getWeightWithBox(), actual.getWeightWithBox());
        assertEquals(expected.getStores().size(), actual.getStores().size());
        for (int i = 0; i < expected.getStores().size(); i++) {
            List<StoreRsDto> expectedStores = expected.getStores();
            List<StoreRsDto> actualStores = actual.getStores();
            assertStoresRsDtos(expectedStores.get(i), actualStores.get(i));
        }
        assertEquals(expected.getParameters().size(), actual.getParameters().size());
        for (int i = 0; i < expected.getParameters().size(); i++) {
            List<ParameterRsDto> expectedParameters = expected.getParameters();
            List<ParameterRsDto> actualParameters = actual.getParameters();
            assertParametersRsDtos(expectedParameters.get(i), actualParameters.get(i));
        }
    }

    public static void assertStoresRsDtos(StoreRsDto expected, StoreRsDto actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCost(), actual.getCost());
        assertEquals(expected.getUrl(), actual.getUrl());
    }

    public static void assertParametersRsDtos(ParameterRsDto expected, ParameterRsDto actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getValue(), actual.getValue());
    }

    public static void assertProducts(Product expected, Product actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getWeight(), actual.getWeight());
        assertEquals(expected.getWeightWithBox(), actual.getWeightWithBox());
        assertEquals(expected.getStores().size(), actual.getStores().size());
        for (int i = 0; i < expected.getStores().size(); i++) {
            List<Store> expectedStores = expected.getStores();
            List<Store> actualStores = actual.getStores();
            assertStores(expectedStores.get(i), actualStores.get(i));
        }
        assertEquals(expected.getParameters().size(), actual.getParameters().size());
        for (int i = 0; i < expected.getParameters().size(); i++) {
            List<Parameter> expectedParameters = expected.getParameters();
            List<Parameter> actualParameters = actual.getParameters();
            assertParameters(expectedParameters.get(i), actualParameters.get(i));
        }
    }

    public static void assertStores(Store expected, Store actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCost(), actual.getCost());
        assertEquals(expected.getUrl(), actual.getUrl());
    }

    public static void assertParameters(Parameter expected, Parameter actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getValue(), actual.getValue());
        assertCharacteristics(expected.getCharacteristic(), actual.getCharacteristic());
    }

    public static void assertCharacteristics(Characteristic expected, Characteristic actual) {
        assertEquals(expected.getName(), actual.getName());
    }

    public static <T> void assertLists(List<? extends T> expected,
                                       List<? extends T> actual,
                                       TwoArgConsumer<? super T> func,
                                       Comparator<? super T> comparator) {
        expected.sort(comparator);
        actual.sort(comparator);
        for (int i = 0; i < expected.size(); i++) {
            func.accept(expected.get(i), actual.get(i));
        }
    }

}

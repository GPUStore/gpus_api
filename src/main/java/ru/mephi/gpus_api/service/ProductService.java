package ru.mephi.gpus_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.Store;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRqDto;
import ru.mephi.gpus_api.entity.products.dto.store.StoreRsDto;
import ru.mephi.gpus_api.exception.ProductWithIdNotFoundException;
import ru.mephi.gpus_api.exception.StoreExistsException;
import ru.mephi.gpus_api.mapper.ProductMapper;
import ru.mephi.gpus_api.mapper.StoreMapper;
import ru.mephi.gpus_api.repository.products.ProductsRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.mephi.gpus_api.validation.Validator.validate;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;
    private final StoreMapper storeMapper;
    private final ProductMapper productMapper;

    public List<ProductRsDto> getAll() {
        return productsRepository.findAll().stream()
                .map(productMapper::entityToDto)
                .toList();
    }

    @Transactional
    public ProductRsDto getById(String id) {
        Product productWithStoresAndCategories =
                productsRepository
                        .findById(id)
                        .orElseThrow(() -> new ProductWithIdNotFoundException(id));
        Product productWithParameters =
                productsRepository
                        .readById(id)
                        .orElseThrow(() -> new ProductWithIdNotFoundException(id));
        return productMapper
                .entityToDto(
                        productWithStoresAndCategories
                                .setParameters(productWithParameters.getParameters()));
    }

    @Transactional
    public List<StoreRsDto> getStoresById(String id) {
        Product product = productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
        return product.getStores().stream()
                .map(storeMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addStoreToProduct(String id, StoreRqDto dto) {
        validate(dto);
        Product product = productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
        Store newStore = storeMapper.dtoToEntity(dto);
        newStore.setProduct(product);
        for (Store store : product.getStores()) {
            if (store.equals(newStore)) {
                throw new StoreExistsException(store.getUrl());
            }
        }
        product.getStores().add(newStore);
        productsRepository.save(product);
    }

    @Transactional
    public void deleteStoreFromProduct(String id, String url) {
        Product product = productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
        product.getStores().removeIf(store -> store.getUrl().equals(url));
        productsRepository.save(product);
    }
}

package ru.mephi.gpus_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.Store;
import ru.mephi.gpus_api.entity.products.dto.StoreRqDto;
import ru.mephi.gpus_api.entity.products.dto.StoreRsDto;
import ru.mephi.gpus_api.exception.ProductWithIdNotFoundException;
import ru.mephi.gpus_api.exception.StoreExistsException;
import ru.mephi.gpus_api.mapper.StoreMapper;
import ru.mephi.gpus_api.repository.products.ProductsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;
    private final StoreMapper storeMapper;

    public List<Product> getAll() {
        return productsRepository.findAll();
    }

    public Product getById(String id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
    }

    public List<StoreRsDto> getStoresById(String id) {
        Product product = productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
        return product.getStores().stream()
                .map(storeMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public void addStoreToProduct(String id, StoreRqDto dto) {
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

    public void deleteStoreFromProduct(String id, String url) {
        Product product = productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
        for (Store store : product.getStores()) {
            if (store.getUrl().equals(url)) {
                product.getStores().remove(store);
            }
        }
        productsRepository.save(product);
    }
}

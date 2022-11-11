package ru.mephi.gpus_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.Store;
import ru.mephi.gpus_api.entity.products.dto.StoreRqDto;
import ru.mephi.gpus_api.entity.products.dto.StoreRsDto;
import ru.mephi.gpus_api.exception.ProductWithIdNotFoundException;
import ru.mephi.gpus_api.exception.StoreExistsException;
import ru.mephi.gpus_api.mapper.StoreMapper;
import ru.mephi.gpus_api.repository.products.ProductsRepository;
import ru.mephi.gpus_api.repository.products.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public List<Product> getAll() {
        return productsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getById(String id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
    }


    public List<StoreRsDto> getStoresById(String id) {
        return storeRepository.findAllByProductId(id).stream()
                .map(storeMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional
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

    @Transactional
    public void deleteStoreFromProduct(String id, String url) {
        Product product = productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
        product.getStores().removeIf(store -> store.getUrl().equals(url));
        productsRepository.save(product);
    }
}

package ru.mephi.gpus_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.exception.ProductWithIdNotFoundException;
import ru.mephi.gpus_api.repository.products.ProductsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;

    @EntityGraph
    public List<Product> getAll() {
        return productsRepository.findAll();
    }

    public Product getById(String id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductWithIdNotFoundException(id));
    }
}

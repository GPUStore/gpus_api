package ru.mephi.gpus_api.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mephi.gpus_api.entity.products.Product;

public interface ProductsRepository extends JpaRepository<Product, String> {
}
package ru.mephi.gpus_api.repository.products;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mephi.gpus_api.entity.products.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, String> {

    @EntityGraph(value = "stores-categories", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Product> findById(String id);

    @EntityGraph(value = "parameters-with-characteristics", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Product> readById(String id);

    @Override
    @EntityGraph(value = "parameters-with-characteristics", type = EntityGraph.EntityGraphType.LOAD)
    List<Product> findAll();
}
package ru.mephi.gpus_api.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.Type;

import java.util.List;

public interface ProductPagAndSortRepository extends PagingAndSortingRepository<Product, String> {

    @EntityGraph(value = "parameters-with-characteristics", type = EntityGraph.EntityGraphType.LOAD)
    Page<Product> findProductById(String id, Pageable pageable);

    @EntityGraph(value = "parameters-with-characteristics", type = EntityGraph.EntityGraphType.LOAD)
    Page<Product> findProductByType(Type type, Pageable pageable);

    @EntityGraph(value = "parameters-with-characteristics", type = EntityGraph.EntityGraphType.LOAD)
    List<Product> findAllByType(Type type, Pageable pageable);

    @Override
    @EntityGraph(value = "parameters-with-characteristics", type = EntityGraph.EntityGraphType.LOAD)
    Page<Product> findAll(Pageable pageable);
}

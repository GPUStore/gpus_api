package ru.mephi.gpus_api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.Type;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;
import ru.mephi.gpus_api.mapper.ProductMapper;
import ru.mephi.gpus_api.repository.products.ProductPagAndSortRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


@Slf4j
@AllArgsConstructor
public class HibernateSearchService {

    private final EntityManager entityManager;
    private final ProductMapper productMapper;
    private final ProductPagAndSortRepository productPagAndSortRepository;

    /**
     * Create an initial Lucene index for the data already present in the
     * database.
     * This method is called when Spring's startup.
     */
    @Transactional
    public void initializeHibernateSearch() {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<ProductRsDto> getAllProductPage(Pageable pageable) {
        Page<Product> productPage = productPagAndSortRepository.findAll(pageable);
        return productPage
                .getContent()
                .stream()
                .map(productMapper::entityToDtoWithParameters)
                .toList();

    }

    public List<ProductRsDto> getProductPageByType(Type type, Pageable pageable) {
        Page<Product> productPage = productPagAndSortRepository.findProductByType(type, pageable);

        return productPage
                .getContent()
                .stream()
                .map(productMapper::entityToDtoWithParameters)
                .toList();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<ProductRsDto> searchProduct(String productText) {
        if (productText.length() < 3)
            return null;

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Product.class).get();

        Query luceneQuery =
                queryBuilder
                        .keyword()
                        .fuzzy()
                        .onFields("name", "country")
                        .matching(productText)
                        .createQuery();

        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);

        try {
            List<Product> result = (List<Product>) jpaQuery.getResultList();
            return result.stream().map(productMapper::entityToDto).toList();
        } catch (NoResultException ignored) {
        }
        return null;
    }

    @Transactional
    public List<String> searchProductNames(String productNameText) {
        List<ProductRsDto> products = searchProduct(productNameText);
        if (products != null) {
            return products.stream().map(ProductRsDto::getName).toList();
        }
        return null;
    }


}

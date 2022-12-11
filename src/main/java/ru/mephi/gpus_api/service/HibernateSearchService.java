package ru.mephi.gpus_api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.gpus_api.entity.products.Product;
import ru.mephi.gpus_api.entity.products.dto.product.ProductRsDto;
import ru.mephi.gpus_api.mapper.ProductMapper;
import ru.mephi.gpus_api.repository.products.ProductsRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;



@Slf4j
@AllArgsConstructor
public class HibernateSearchService {
    private final EntityManager entityManager;
    private final ProductMapper productMapper;


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

    @Transactional
    public List<ProductRsDto> searchProduct(String productText){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Product.class).get();


        // a very basic query by keywords
        Query luceneQuery =
                queryBuilder
                        .keyword()
                        .fuzzy()
                        .withEditDistanceUpTo(1)
                        .withPrefixLength(1)
                        .onFields("name", "country")
                        .matching(productText)
                        .createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Product.class);

        // execute search and return results (sorted by relevance as default)

        List<Product> result = null;
        try {

           // List enities = jpaQuery.getResultList();
            result = (List<Product> ) jpaQuery.getResultList();
            return result.stream().map(productMapper::entityToDto).toList();
        } catch (NoResultException nre) {
            ;// do nothing

        }
        return null;
    }
}

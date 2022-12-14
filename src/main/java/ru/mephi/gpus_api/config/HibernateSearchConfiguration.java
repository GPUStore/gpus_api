package ru.mephi.gpus_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mephi.gpus_api.mapper.ProductMapper;
import ru.mephi.gpus_api.repository.products.ProductPagAndSortRepository;
import ru.mephi.gpus_api.service.HibernateSearchService;

import java.util.Objects;

@Configuration
public class HibernateSearchConfiguration {

    @Bean
    HibernateSearchService hibernateSearchService(
            @Autowired ProductPagAndSortRepository productPagAndSortRepository,
            @Autowired ProductDatabaseConfig productDatabaseConfig,
            @Autowired ProductMapper productMapper) {
        HibernateSearchService hibernateSearchService =
                new HibernateSearchService(Objects.requireNonNull(
                                productDatabaseConfig.productEntityManager()
                                        .getObject())
                        .createEntityManager()
                        , productMapper,
                        productPagAndSortRepository
                );
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}

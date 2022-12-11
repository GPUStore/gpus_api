package ru.mephi.gpus_api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.mephi.gpus_api.mapper.ProductMapper;
import ru.mephi.gpus_api.service.HibernateSearchService;

import javax.persistence.EntityManager;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class HibernateSearchConfiguration {
    private final ProductDatabaseConfig productDatabaseConfig;
    private final ProductMapper productMapper;

    @Bean
    HibernateSearchService hibernateSearchService() {
        HibernateSearchService hibernateSearchService = new HibernateSearchService(Objects.requireNonNull(
                productDatabaseConfig.productEntityManager()
                        .getObject())
                .createEntityManager()
                , productMapper);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}

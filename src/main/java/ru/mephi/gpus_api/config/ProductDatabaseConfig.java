package ru.mephi.gpus_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.mephi.gpus_api.entity.products.Product;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = ProductDatabaseConfig.REPOSITORY_PACKAGE,
        entityManagerFactoryRef = "productEntityManager",
        transactionManagerRef = "productTransactionManager"
)
public class ProductDatabaseConfig {
    private static final String PREFIX_TO_PROPERTIES = "spring.second-datasource";
    public static final String REPOSITORY_PACKAGE = "ru.mephi.gpus_api.repository.products";
    private static final String ENTITY_PACKAGE = Product.class.getPackageName();

    @Bean
    @ConfigurationProperties(PREFIX_TO_PROPERTIES)
    public DataSource productDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean productEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(productDataSource());
        em.setPackagesToScan(ENTITY_PACKAGE);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean
    public PlatformTransactionManager productTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(productEntityManager().getObject());
        return transactionManager;
    }
}
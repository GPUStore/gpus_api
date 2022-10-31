package ru.mephi.gpus_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.mephi.gpus_api.entity.clients.Client;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = ClientDatabaseConfig.REPOSITORY_PACKAGE,
        entityManagerFactoryRef = "clientEntityManager",
        transactionManagerRef = "clientTransactionManager"
)
public class ClientDatabaseConfig {

    public static final String REPOSITORY_PACKAGE = "ru.mephi.gpus_api.repository.clients";
    private static final String ENTITY_PACKAGE = Client.class.getPackageName();

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource clientDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean clientEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(clientDataSource());
        em.setPackagesToScan(ENTITY_PACKAGE);
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(new Properties());
        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager clientTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(clientEntityManager().getObject());
        return transactionManager;
    }
}
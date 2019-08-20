package com.foobar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = { "com.foobar.persistence.foo" })
@Slf4j
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    String datasourceURL;

    @Value("${spring.datasource.username}")
    String dataSourceUserName;

    @Value("${spring.datasource.password}")
    String dataSourceUserPassword;

    @Value("${spring.datasource.database.driverClassName}")
    String driverClassName;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        log.trace("Initializing PostgreSQL datasource");
        final DataSource dataSource = buildDataSource();
        return dataSource;
    }

    private DataSource buildDataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setPoolName("first");
            hikariConfig.setJdbcUrl(datasourceURL);
            hikariConfig.setUsername(dataSourceUserName);
            hikariConfig.setPassword(dataSourceUserPassword);
            hikariConfig.setDriverClassName(driverClassName);
            hikariConfig.setLeakDetectionThreshold(20000);
        return new HikariDataSource(hikariConfig);
    }


    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.foobar.domain.foo")
                .persistenceUnit("foo")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

package com.foobar.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
        entityManagerFactoryRef = "barEntityManagerFactory",
        transactionManagerRef = "barTransactionManager",
        basePackages = { "com.foobar.persistence.bar" })
@Slf4j
public class DataSource2Config {

    @Value("${spring.datasource.second.url}")
    String secondDatasourceURL;

    @Value("${spring.datasource.second.username}")
    String secondDataSourceUserName;

    @Value("${spring.datasource.second.password}")
    String secondDataSourceUserPassword;

    @Value("${spring.datasource.second.database.driverClassName}")
    String driverClassName;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    //dataSource2 is used below as @Qualifier("dataSource2")
    public DataSource dataSource2() {
        log.trace("Initializing PostgreSQL datasource 2");
        final DataSource dataSource = buildDataSource();
        return dataSource;
    }

    private DataSource buildDataSource() {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("second");
        hikariConfig.setJdbcUrl(secondDatasourceURL);
//        hikariConfig.setReadOnly(true);
        hikariConfig.setUsername(secondDataSourceUserName);
        hikariConfig.setPassword(secondDataSourceUserPassword);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setLeakDetectionThreshold(20000);
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "barEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource2") DataSource dataSource  // dataSource2 is correct? yes. worked!!
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.foobar.domain.bar")
                        .persistenceUnit("bar")
                        .build();
    }

    @Bean(name = "barTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("barEntityManagerFactory") EntityManagerFactory
                    barEntityManagerFactory
    ) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }
}


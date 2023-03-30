package com.cpg.cloudrunprocessor.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class Config {


    private final DataSourceProperties dataSourceProperties;

    @Autowired
    public Config(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }


    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/public/**")
                .build();
    }

    @Bean
    public String tst() {
        System.out.println("version-1-debug");
        return "";
    }


    //    @Bean
//    public DataSource1 dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl(dataSourceProperties.getUrl());
//        dataSource.setUsername(dataSourceProperties.getUsername());
//        dataSource.setPassword(dataSourceProperties.getPassword());
//        return dataSource;
//    }
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl(dataSourceProperties.getUrl());
//        dataSource.setUsername(dataSourceProperties.getUsername());
//        dataSource.setPassword(dataSourceProperties.getPassword());
//        dataSource.setMaximumPoolSize(5);
//        dataSource.setMinimumIdle(2);
//
//        return dataSource;
//    }

}

package com.mosaic.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig
{
    @Bean
    SpringLiquibase liquibase(DataSource dataSource)
    {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}

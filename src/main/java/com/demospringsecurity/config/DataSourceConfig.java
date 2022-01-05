package com.demospringsecurity.config;

import com.demospringsecurity.services.DotenvService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Autowired
    DotenvService dotenvService;
    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        //Config bằng hikariDataSource
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dotenvService.getValueDotenv("DB_CLASSNAME"));
        hikariConfig.setJdbcUrl(dotenvService.getValueDotenv("DB_URL"));
        hikariConfig.setUsername(dotenvService.getValueDotenv("DB_USERNAME"));
        hikariConfig.setPassword(dotenvService.getValueDotenv("DB_PASSWORD"));
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }
}

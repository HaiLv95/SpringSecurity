package com.demospringsecurity.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        Dotenv dotenv = Dotenv.configure()
                .directory("E:\\Java\\Java5\\Project\\SpringSecurity")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dotenv.get("DB_CLASSNAME"));
        hikariConfig.setJdbcUrl(dotenv.get("DB_URL"));
        hikariConfig.setUsername(dotenv.get("DB_USERNAME"));
        hikariConfig.setPassword(dotenv.get("DB_PASSWORD"));
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }
}

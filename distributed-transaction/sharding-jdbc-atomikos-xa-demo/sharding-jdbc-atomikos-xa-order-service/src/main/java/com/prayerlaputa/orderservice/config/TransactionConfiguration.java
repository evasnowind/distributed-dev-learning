package com.prayerlaputa.orderservice.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


/**
 * @author chenglong.yu
 * created on 2020/12/8
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = null;
        try {
            File file = new ClassPathResource("sharding-jdbc.yml").getFile();
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(file);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }


    @Bean
    public PlatformTransactionManager txManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

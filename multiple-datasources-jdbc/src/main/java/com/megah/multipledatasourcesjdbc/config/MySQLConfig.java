package com.megah.multipledatasourcesjdbc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MySQLConfig {
	
//	@Bean(name = "mysql")
//	@ConfigurationProperties(prefix = "spring.datasource.mysql")
//	public DataSource mysqlDataSource() {
//		return DataSourceBuilder.create().build();
//	}
	
	@Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

	@Bean(name = "mysql")
	@ConfigurationProperties("spring.datasource.mysql.hikari")
	public DataSource mysqlDataSource() {
	    return mysqlDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean(name = "jdbcTemplateMysql")
	public JdbcTemplate jdbcTemplateMysql(@Qualifier("mysql") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}

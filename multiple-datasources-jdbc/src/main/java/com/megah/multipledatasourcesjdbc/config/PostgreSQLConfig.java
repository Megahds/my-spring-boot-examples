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
public class PostgreSQLConfig {
	
//	@Bean(name = "postgresql")
//	@ConfigurationProperties(prefix = "spring.datasource.postgresql")
//	public DataSource postgresqlDataSource() {
//		return DataSourceBuilder.create().build();
//	}
	
	@Bean
    @ConfigurationProperties("spring.datasource.postgresql")
    public DataSourceProperties postgresqlDataSourceProperties() {
        return new DataSourceProperties();
    }

	@Bean(name = "postgresql")
	@ConfigurationProperties("spring.datasource.postgresql.hikari")
	public DataSource postgresqlDataSource() {
	    return postgresqlDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean(name = "jdbcTemplatePostgresql")
	public JdbcTemplate jdbcTemplatePostgresql(@Qualifier("postgresql") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}

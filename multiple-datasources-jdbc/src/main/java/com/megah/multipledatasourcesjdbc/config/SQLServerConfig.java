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
public class SQLServerConfig {

//	@Bean(name = "sqlserver")
//	@ConfigurationProperties(prefix = "spring.datasource.sqlserver")
//	public DataSource sqlserverDataSource() {
//		return DataSourceBuilder.create().build();
//	}
	
	@Bean
    @ConfigurationProperties("spring.datasource.sqlserver")
    public DataSourceProperties sqlserverDataSourceProperties() {
        return new DataSourceProperties();
    }

	@Bean(name = "sqlserver")
	@ConfigurationProperties("spring.datasource.sqlserver.hikari")
	public DataSource sqlserverDataSource() {
	    return sqlserverDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean(name = "jdbcTemplateSqlserver")
	public JdbcTemplate jdbcTemplateSqlserver(@Qualifier("sqlserver") DataSource ds) {
		return new JdbcTemplate(ds);
	}
}

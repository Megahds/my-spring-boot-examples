package com.megah.multipledatasourcesjpa.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "postgresqlEntityManagerFactory", 
						transactionManagerRef = "postgresqlTransactionManager", 
						basePackages = "com.megah.multipledatasourcesjpa.repository.postgresql")
public class PostgreSQLConfig {

	@Bean(name = "postgresqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.postgresql")
	public DataSource postgresqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
//	@Bean
//    @ConfigurationProperties("spring.datasource.postgresql")
//    public DataSourceProperties postgresqlDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "postgresqlDataSource")
//    @ConfigurationProperties("spring.datasource.postgresql.hikari")
//    public DataSource postgresqlDataSource() {
//        return postgresqlDataSourceProperties()
//          .initializeDataSourceBuilder()
//          .build();
//    }

	@Bean(name = "postgresqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("postgresqlDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.megah.multipledatasourcesjpa.model.postgresql")
				.persistenceUnit("postgresql").build();
	}

	@Bean(name = "postgresqlTransactionManager")
	public PlatformTransactionManager postgresqlTransactionManager(
			@Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}

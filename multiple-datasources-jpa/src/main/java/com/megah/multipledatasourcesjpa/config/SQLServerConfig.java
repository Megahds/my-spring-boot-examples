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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "sqlserverEntityManagerFactory", 
						transactionManagerRef = "sqlserverTransactionManager", 
						basePackages = "com.megah.multipledatasourcesjpa.repository.sqlserver")
public class SQLServerConfig {

	@Primary
	@Bean(name = "sqlserverDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.sqlserver")
	public DataSource sqlserverDataSource() {
		return DataSourceBuilder.create().build();
	}
	
//	@Bean
//    @ConfigurationProperties("spring.datasource.sqlserver")
//    public DataSourceProperties sqlserverDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Primary
//    @Bean(name = "sqlserverDataSource")
//    @ConfigurationProperties("spring.datasource.sqlserver.hikari")
//    public DataSource sqlserverDataSource() {
//        return sqlserverDataSourceProperties()
//          .initializeDataSourceBuilder()
//          .build();
//    }

	@Primary
	@Bean(name = "sqlserverEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean sqlserverEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("sqlserverDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.megah.multipledatasourcesjpa.model.sqlserver")
				.persistenceUnit("sqlserver").build();
	}

	@Primary
	@Bean(name = "sqlserverTransactionManager")
	public PlatformTransactionManager sqlserverTransactionManager(
			@Qualifier("sqlserverEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}

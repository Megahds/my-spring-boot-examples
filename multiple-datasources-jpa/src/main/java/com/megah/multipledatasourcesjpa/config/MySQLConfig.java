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
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory", 
						transactionManagerRef = "mysqlTransactionManager", 
						basePackages = "com.megah.multipledatasourcesjpa.repository.mysql")
public class MySQLConfig {

	@Bean(name = "mysqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
//	@Bean
//    @ConfigurationProperties("spring.datasource.mysql")
//    public DataSourceProperties mysqlDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties("spring.datasource.mysql.hikari")
//    public DataSource mysqlDataSource() {
//        return mysqlDataSourceProperties()
//          .initializeDataSourceBuilder()
//          .build();
//    }

	@Bean(name = "mysqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mysqlDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.megah.multipledatasourcesjpa.model.mysql")
				.persistenceUnit("mysql").build();
	}

	@Bean(name = "mysqlTransactionManager")
	public PlatformTransactionManager mysqlTransactionManager(
			@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}

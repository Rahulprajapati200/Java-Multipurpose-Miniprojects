package com.connection.multiDataBase.config.product;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db1EntityManagerFactory",
						transactionManagerRef = "db1TransactionManager",
						basePackages = "com.connection.multiDataBase.repo.product"
)
public class DbOneConfig {
	
	//DataSource
	@Primary
	@Bean(name = "db1DataSource")
	@ConfigurationProperties("db1.datasource")
	 DataSource db1DataSource() {
		return DataSourceBuilder.create().build();
		
	}
	//Entity Manager Factory.
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder emf,
			@Qualifier("db1DataSource") DataSource dataSource) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return emf.dataSource(dataSource)
				.packages("com.connection.multiDataBase.model.product")
				.properties(properties)
				.build()
				;
		
	}
	//TxManager
	@Primary
	@Bean
	public PlatformTransactionManager db1TransactionManager(@Qualifier("db1EntityManagerFactory") 
	EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
		}

}

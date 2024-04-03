package com.connection.multiDataBase.config.customer;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory",
						transactionManagerRef = "db2TransactionManager",
						basePackages = "com.connection.multiDataBase.repo.customer"
)
public class DbTwoConfig {
	
	//DataSource
	@Bean(name = "db2DataSource")
	@ConfigurationProperties("db2.datasource")
	 DataSource db2DataSource() {
		return DataSourceBuilder.create().build();
		
	}
	//Entity Manager Factory.
	@Bean
	public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder emf,
			@Qualifier("db2DataSource") DataSource dataSource) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return emf.dataSource(dataSource)
				.packages("com.connection.multiDataBase.model.customer")
				.properties(properties)
				.build()
				;
		
	}
	//TxManager
	@Bean
	public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory") 
	EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
		}

}


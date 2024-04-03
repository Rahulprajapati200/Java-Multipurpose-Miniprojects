package com.connection.multiDataBase;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.connection.multiDataBase.model.customer.Customer;
import com.connection.multiDataBase.model.product.Product;
import com.connection.multiDataBase.repo.customer.CustomerRepository;
import com.connection.multiDataBase.repo.product.ProductRepository;

@SpringBootApplication
public class MultiDataBaseApplication implements CommandLineRunner {
	
	@Autowired
	public ProductRepository prodRepo;
	
	@Autowired
	public CustomerRepository custRepo;

	public static void main(String[] args) {
		SpringApplication.run(MultiDataBaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		prodRepo.saveAll(Arrays.asList(
		        new Product(1, "p1", "Pen"),
		        new Product(2, "p2", "Book"),
		        new Product(3, "p3", "Notebook")
		    ));
		    
		    custRepo.saveAll(Arrays.asList(
		        new Customer(1, "sam@gmail.com", "Sam"),
		        new Customer(2, "neet@gmail.com", "Neet"),
		        new Customer(3, "youth@gmail.com", "Youth")
		    ));
		
	}

}

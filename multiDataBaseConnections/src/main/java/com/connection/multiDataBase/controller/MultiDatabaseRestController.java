package com.connection.multiDataBase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connection.multiDataBase.model.customer.Customer;
import com.connection.multiDataBase.model.product.Product;
import com.connection.multiDataBase.repo.customer.CustomerRepository;
import com.connection.multiDataBase.repo.product.ProductRepository;

@RestController
public class MultiDatabaseRestController {
	@Autowired
	public ProductRepository prodRepo;
	
	@Autowired
	public CustomerRepository custRepo;
	
	@GetMapping("/products")
	private List<Product> getAllProducts(){
		return prodRepo.findAll();
	}
	
	@GetMapping("/customers")
	private List<Customer> getAllCustomers(){
		return custRepo.findAll();
	}

}

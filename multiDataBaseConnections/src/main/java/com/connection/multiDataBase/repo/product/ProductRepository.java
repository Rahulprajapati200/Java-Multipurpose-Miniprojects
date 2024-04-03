package com.connection.multiDataBase.repo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connection.multiDataBase.model.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	

}

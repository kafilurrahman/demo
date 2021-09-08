package com.boot.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.boot.project.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	//select * from Product where productName=?
	public List<Product> findByProductName(String productName);
    
	public List<Product> findByPriceBetween(int lowerPrice, int upperPrice);
	
	//public List<Product> findByProductNameContainingAndPriceBetween(String productName,int lowerPrice, int upperPrice);

}

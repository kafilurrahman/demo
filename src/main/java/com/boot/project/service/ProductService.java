package com.boot.project.service;

import java.util.List;

import com.boot.project.model.Product;

public interface ProductService {
	public boolean addProduct(Product product);
	public boolean deleteProduct(int productId);
	public boolean updateProduct(Product product);
	public Product getProductById(int productId);
	public List<Product> getProductByName(String productName);
	public List<Product> getAllProduct();
	public boolean isProductExist(int productId);
	public List<Product> filterByPrice(int lowerPrice, int upperPrice);
	//public List<Product> filterByBrand(String productName,int lowerPrice,int upperPrice);

}

package com.boot.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.project.model.Product;
import com.boot.project.service.ProductService;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getProducts()
	{
		ResponseEntity<List<Product>> responseEntity=null;
		List<Product> product=productService.getAllProduct();
		if(product.size()==0)
		{
			responseEntity=new ResponseEntity<List<Product>>(product,HttpStatus.NO_CONTENT);
		}
		else
		{
			responseEntity=new ResponseEntity<List<Product>>(product,HttpStatus.OK);
			
		}
		return responseEntity;
	
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId")int productId)
	{
		System.out.println("this is product with product id: " + productId);
		ResponseEntity<Product> responseEntity=null;
		Product product=new Product();
		if(productService.isProductExist(productId))
		{
			responseEntity=new ResponseEntity<Product>(product,HttpStatus.FOUND);
		}
		else
		{
			responseEntity=new ResponseEntity<Product>(product,HttpStatus.NO_CONTENT);			
		}
		return responseEntity;
	}
	
	@GetMapping("/productBy/{productName}")
	public List<Product> getProductByName(@PathVariable("productName")String pname)
	{
		System.out.println("this is product with product name: " + pname);
		return productService.getProductByName(pname);
	}
	
	@PutMapping
	public ResponseEntity<String> updateProduct(@RequestBody Product product)
	{
		System.out.println("product updated");
		ResponseEntity<String> responseEntity=null;
		int productId=product.getProductId();
		String message=null;
		if(productService.isProductExist(productId))
		{
			productService.updateProduct(product);
			message="product with productid: "+productId+" updated successfully";
			responseEntity=new ResponseEntity<String>(message,HttpStatus.OK);
		}
		else
		{
			message="product with productid:  "+productId+" saved successfully";
			responseEntity=new ResponseEntity<String>(message,HttpStatus.NO_CONTENT);
			
		}
		return responseEntity;

	}
	
	
	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody Product product)
	{
	ResponseEntity<String> responseEntity=null;
	int productId=product.getProductId();
	String message=null;
	if(productService.isProductExist(productId))
	{
		message="product with productid: "+productId+" allready Exist";
		responseEntity=new ResponseEntity<String>(message,HttpStatus.CONFLICT);
	}
	else
	{
		productService.addProduct(product);
		message="product with productid:  "+productId+" saved successfully";
		responseEntity=new ResponseEntity<String>(message,HttpStatus.CREATED);
		
	}
	return responseEntity;
	}
	
	
	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")int productId) {
		System.out.println("this is product with product id: " + productId+" deleted");
		ResponseEntity<String> responseEntity=null;
		String message=null;
		if(productService.isProductExist(productId))
		{
			productService.deleteProduct(productId);
			message="product with productid: "+productId+"deleted successfully";
			responseEntity=new ResponseEntity<String>(message,HttpStatus.OK);
		}
		else
		{
			message="product with productid:  "+productId+" does not exist";
			responseEntity=new ResponseEntity<String>(message,HttpStatus.NO_CONTENT);
			
		}
		return responseEntity;
	}
	
	
	
	@GetMapping("filterByPrice/{lower}/{upper}")
	public List<Product> filterByPricee(@PathVariable("lower")int lower,@PathVariable("upper")int upper)
	{
		System.out.println("filter by price called");
		return productService.filterByPrice(lower, upper);
		
	}
}

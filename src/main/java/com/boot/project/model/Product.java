package com.boot.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "productdata")
public class Product {
	@Id
	private int productId;
	private String productName;
	private int quantityOnHand;
	private int price;

}

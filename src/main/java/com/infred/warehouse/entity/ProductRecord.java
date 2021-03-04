package com.infred.warehouse.entity;

public class ProductRecord {

	
	Product prod;
	ProductStatusEnum prodStatus;
	
	
	
	
	public ProductRecord(Product prod2, ProductStatusEnum status) {
		this.prod = prod2;
		this.prodStatus = status;
	}




	public Product getProd() {
		return prod;
	}




	public void setProd(Product prod) {
		this.prod = prod;
	}




	public ProductStatusEnum getProdStatus() {
		return prodStatus;
	}




	public void setProdStatus(ProductStatusEnum prodStatus) {
		this.prodStatus = prodStatus;
	}




	
	
	
	
}

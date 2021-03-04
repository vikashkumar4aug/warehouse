package com.infred.warehouse.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infred.warehouse.ErrorHandling.ProductNotFoundException;
import com.infred.warehouse.entity.Product;
import com.infred.warehouse.entity.ProductRecord;
import com.infred.warehouse.service.InventoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api("Warehouse controller")
@RestController
public class InventoryController {
	@Autowired
	InventoryService inventoryService;
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product)
			 {
		return inventoryService.insert(product);
	}
	
	@ApiOperation(value = "delete product")
	@RequestMapping(method = RequestMethod.DELETE,value ="removeProduct/{id}")
    public int removeProduct(@PathVariable Integer id) {
		if(id.equals(null))
			throw new ProductNotFoundException("Product Id cant't be null");
		return inventoryService.deleteProduct(id);
    }
	
	@RequestMapping(value = "/viewProductDetailsById/{id}",method = RequestMethod.GET)
	public Product viewProductDetailsById(@PathVariable Integer id) throws ProductNotFoundException
			 {
		if(id.equals(null))
			throw new ProductNotFoundException("Product Id cant't be null");
		 Product prod=inventoryService.viewProductDetailsById(id);
		 if(prod==null){
			 throw new  ProductNotFoundException("Product not found with id -->"+ id);
		 }
		 return prod;
	}
	
	@RequestMapping(value = "/viewProductDetails",method = RequestMethod.GET)
	public List<Product> viewProductDetails()
			 {
		return inventoryService.viewProductDetails();
	}
	
	@PostMapping(value="/purchase/{id}")
    public Product purchase(@PathVariable Integer id){
		if(id.equals(null))
			throw new ProductNotFoundException("Product Id cant't be null");
		return inventoryService.purchase(id);
		
	}
	
	@RequestMapping(value="/reporting/{startDateTime}/{endDateTime}" ,method = RequestMethod.GET)
	public List<ProductRecord> reporting(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime startDateTime,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime){
		return inventoryService.reporting(startDateTime,endDateTime);
		
	}
}

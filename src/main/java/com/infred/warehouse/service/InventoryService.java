package com.infred.warehouse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infred.warehouse.ErrorHandling.ProductNotFoundException;
import com.infred.warehouse.entity.Product;
import com.infred.warehouse.entity.ProductRecord;
import com.infred.warehouse.repository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	InventoryRepository inventoryRepository;
	 public Product insert(Product product) {
		 return inventoryRepository.createProduct(product);
	    }

	    public Product viewProductDetailsById(Integer id){
	        return inventoryRepository.findByProdId(id);
	    }
	    
	    public List<Product> viewProductDetails() {
	        return inventoryRepository.viewProductDetails();
	    }
	    
	    public int deleteProduct(Integer  id) {
	    	return inventoryRepository.deleteProduct(id);
	    }
	    
	    public Product purchase(Integer id){
	    	return inventoryRepository.purchase(id);
	    }
	    
	    public List<ProductRecord> reporting(LocalDateTime startDateTime,LocalDateTime endDateTime){
	    	
	    	 return inventoryRepository.reporting(startDateTime, endDateTime);
	    	
	    }
}

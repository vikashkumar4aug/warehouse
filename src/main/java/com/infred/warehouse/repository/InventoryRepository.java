package com.infred.warehouse.repository;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Repository;

import com.infred.warehouse.ErrorHandling.ProductNotFoundException;
import com.infred.warehouse.entity.Product;
import com.infred.warehouse.entity.ProductRecord;
import com.infred.warehouse.entity.ProductStatusEnum;

@Repository
public class InventoryRepository {
Random generator =new Random();
List<Product> products = new ArrayList<>();
Map<Product,Integer> inventories= new HashMap<>();
Integer availableProduct=0;
SortedMap<LocalDateTime,ProductRecord> prodReportings= new TreeMap<>();


public Product createProduct(Product prod)
{
	prod.setProdId(generator.nextInt());
	
	products.add(prod);
	inventories.put(prod,1);
	prodReportings.put(LocalDateTime.now(),new ProductRecord(prod,ProductStatusEnum.ADDED));
	
	return prod;
}

public Product purchase(Integer id){
	Product prod =new Product(id);
	Integer prodCount=inventories.get(prod);
   if(prodCount<=0){
		throw new ProductNotFoundException("Product out of stock"+id);
	}
   inventories.put(prod, inventories.get(prod) -1);
   
	
	return prod;
	
	
}

public List<ProductRecord> reporting(LocalDateTime startDateTime,LocalDateTime endDateTime){
	SortedMap<LocalDateTime,ProductRecord> prodReportingsResults= prodReportings.subMap(startDateTime, endDateTime) ;
//	List<String> result2 = new ArrayList(map.values());
	  List<ProductRecord> prodReporting= new ArrayList<ProductRecord>(prodReportingsResults.values());
	  return prodReporting;
	
}

public int deleteProduct(Integer id) throws ProductNotFoundException{

	int prdId = 0;
	Product prod = null;
	Iterator<Product> it = products.iterator();
	while (it.hasNext()) {

		prod = it.next();
		if(prod.getProdId().equals(id)){
			prdId=prod.getProdId();
			it.remove();
//			return prod.getProdId();
		}
     }
	prodReportings.put(LocalDateTime.now(),new ProductRecord(prod,ProductStatusEnum.REMOVED));
	return prdId;
}

public Product findByProdId(Integer id) throws ProductNotFoundException
{
	

	Product prodDetail=null;
	Iterator<Product> it = products.iterator();
	while (it.hasNext()) {

		prodDetail = it.next();
		if(prodDetail.getProdId().equals(id)){
			
			
		return prodDetail;
		
	}
	}
	return null;
}

public List<Product> viewProductDetails()
{
	
	return products;
}

}

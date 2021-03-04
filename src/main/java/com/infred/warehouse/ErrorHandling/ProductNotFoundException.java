package com.infred.warehouse.ErrorHandling;


public class ProductNotFoundException  extends RuntimeException{
	
	private static final long serialVersionUID = -9079454849611061074L;

	
	 public ProductNotFoundException(String exception) {
	        super(exception);
	    }

}

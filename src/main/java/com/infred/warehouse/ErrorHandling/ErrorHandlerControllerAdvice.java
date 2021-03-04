package com.infred.warehouse.ErrorHandling;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlerControllerAdvice {


	   @ExceptionHandler(ProductNotFoundException.class)
	    public final ResponseEntity<Object> handleUserNotFoundException(ProductNotFoundException ex) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Record Not Found", details);
	        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
	    }

}

package com.sparklink.employeeservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sparklink.employeeservice.model.ExceptionResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ControllerAdvisorGlobalExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)  
	//override method of ResponseEntityExceptionHandler class  
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)  
	{  
		//creating exception response structure  
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  
		//returning exception structure and specific status   
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);  
	}
	
	
	//********************************* General Exceptions Start *********************************************//
	
	// Handle NullPonter Exception due to record not found 
	@ExceptionHandler(RecordNotFoundNullPointerException.class)
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundNullPointerException rnfnpe, WebRequest request){
		ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), rnfnpe.getMessage(), request.getDescription(false));  
      	//returning exception structure and specific status   
      	return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NO_CONTENT);  
	}
	
	//********************************** General Exceptions End	 *********************************************//

	
	//********************************* Employee_Master Start *********************************************//
	
    @ExceptionHandler(EmployeeInfoByIDNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeInfoByIDNotFoundException(EmployeeInfoByIDNotFoundException ex, WebRequest request) {

		/*
		 * Map<String, Object> body = new LinkedHashMap<>(); body.put("timestamp",
		 * LocalDateTime.now()); body.put("message", "Employee by ID not found"); //
		 * Displayed message on page for User
		 * 
		 * return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		 */        
    	//creating exception response structure  
      	ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  
      	//returning exception structure and specific status   
      	return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);  
        
    }

    @ExceptionHandler(EmployeeInfoByNameNotFoundException.class)
    public ResponseEntity<Object> handleEmployeeInfoByNameNotFoundException(EmployeeInfoByNameNotFoundException ex, WebRequest request) {

		/*
		 * Map<String, Object> body = new LinkedHashMap<>(); body.put("timestamp",
		 * LocalDateTime.now()); body.put("message", "Employee by Name not found."); //
		 * Displayed message on page for User
		 * 
		 * return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		 */
    	//creating exception response structure  
      	ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));  
      	//returning exception structure and specific status   
      	return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);  
    }
    
    @ExceptionHandler(NoEmployeeDataFoundException.class)
    public ResponseEntity<Object> handleNoEmployeeDataFoundException(NoEmployeeDataFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No Employee Record found"); // Displayed message on page for User

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	//********************************* Employee_Master End *********************************************//
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

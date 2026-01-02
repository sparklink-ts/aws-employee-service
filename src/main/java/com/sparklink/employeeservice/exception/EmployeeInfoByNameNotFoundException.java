package com.sparklink.employeeservice.exception;


public class EmployeeInfoByNameNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public EmployeeInfoByNameNotFoundException(String employeeName) {

		super(String.format("Employee with Name:-   %s   not found", employeeName)); // Displayed message on Console for reference
	}
}

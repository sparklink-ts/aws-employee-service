package com.sparklink.employeeservice.exception;



public class EmployeeInfoByIDNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeInfoByIDNotFoundException(int employeeId) {

		super(String.format("Employee with Id %d not found", employeeId)); // Displayed message on Console for reference
	}
}

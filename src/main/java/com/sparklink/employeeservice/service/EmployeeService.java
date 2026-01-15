package com.sparklink.employeeservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sparklink.employeeservice.exception.EmployeeInfoByIDNotFoundException;
import com.sparklink.employeeservice.exception.EmployeeInfoByNameNotFoundException;
import com.sparklink.employeeservice.exception.NoEmployeeDataFoundException;
import com.sparklink.employeeservice.model.*;
import com.sparklink.employeeservice.repository.EmployeeRepository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service("employeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	RestTemplate restTemplate;
	
	private Boolean matched = true;
	private Boolean dockerService = true;
	private List<Employee_Complete_Details_View> employeeCompleteDetailsViewList;
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	//------------ Get All Employee Details from Employee DB, Address DB and Department DB --------------------------//

	//REST Call
	@Value("${rest.get.url.employeeIDListFromAddress}")
	String employeeIDListFromAddress;

	@Value("${rest.get.url.addressByEmployeeID}")
	String addressByEmployeeID;

	@Value("${rest.get.url.allDepartmentEmployeeID}")
	String allDepartmentEmployeeID;

	@Value("${rest.get.url.departmentByEmployeeID}")
	String departmentByEmployeeID;

	//Docker Service REST Call
	@Value("${rest.get.url.docker.service.employeeIDListFromAddress}")
	String docker_service_employeeIDListFromAddress;

	@Value("${rest.get.url.docker.service.addressByEmployeeID}")
	String docker_service_addressByEmployeeID;

	@Value("${rest.get.url.docker.service.allDepartmentEmployeeID}")
	String docker_service_allDepartmentEmployeeID;

	@Value("${rest.get.url.docker.service.departmentByEmployeeID}")
	String docker_service_departmentByEmployeeID;


	//------------ Get All Employee Details from Employee DB, Address DB and Department DB --------------------------//


	// ****************** Calling from FrontController ********************** //

	public List<Employee_Master> getAllEmployee() {
		List<Employee_Master> employeeList = new ArrayList<>();
		try{
			 employeeList = employeeRepository.getAllEmployee();
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeList;
	}

	@Deprecated
	public List<Employee_Master> getEmployeeDetails() {
		List<Employee_Master> employeeList = new ArrayList<>();
		try{
			employeeList = employeeRepository.getEmployeeDetails();
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeList;
	}

	// Database based : Fetching data on the basis of SQL querie logic
	@Deprecated
	public List<Employee_Complete_Details_View> getEmployeeCompleteDetails() {
		employeeCompleteDetailsViewList = new ArrayList<>();
		try{
			employeeCompleteDetailsViewList = employeeRepository.getEmployeeCompleteDetails();
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeCompleteDetailsViewList;
	}

	@Deprecated
	public Employee_Master getEmployeeDetailsById(int employeeId) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		Employee_Master employee = employeeRepository.getEmployeeDetailsById(employeeId);
			if(employee == null) throw new EmployeeInfoByIDNotFoundException(employeeId);
		return employee;
	}
	
	public Employee_Master addEmployeeDetails(Employee_Master employee) {
		return employeeRepository.addEmployeeDetails(employee);
	}
	
	public void updateEmployeeDetails(Employee_Master employee) {
		employeeRepository.updateEmployeeDetails(employee);
	}
	
	public void updateEmployeeDetailsById(Employee_Master employee, int employeeId) {
		employeeRepository.updateEmployeeDetailsById(employee, employeeId);
	}

	public Boolean deleteEmployeeDetails(int employeeId) {
		matched = employeeRepository.deleteEmployeeDetails(employeeId);
		if(!matched)
			throw new EmployeeInfoByIDNotFoundException(employeeId);

		return matched;
	}

	@Deprecated
	public List<Employee_Master> getDepartmentDetailsByEmployeeID(int employeeId) {
		List<Employee_Master> employeeList= null;
		try {
			employeeList = employeeRepository.getDepartmentDetailsByEmployeeID(employeeId);
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {

		}
		return employeeList;
	}

/*
	@Deprecated
	public List<Employee_Master> CallHCQLQueries(){
		return employeeRepository.CallHCQLQueries();
	}
*/

	public String getSubscriptionMessage(String user) {
		return "Hello "+user+", Thanks for the subscription!";
	}

	// ****************** @NamedQueries ********************** //

	@Deprecated
	public List<Employee_Master> getEmployeeDetailsByName(String employeeName) {
		
		//return employeeRepository.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));;
		List<Employee_Master> employeeList = employeeRepository.getEmployeeDetailsByName(employeeName);
			if(employeeList == null) throw new EmployeeInfoByNameNotFoundException(employeeName);
		return employeeList;
	}

	// ****************** @NamedQueries ********************** //

	// ****************** Microservice Call Using - REST API ********************** //

	public List<Employee_Master> getRESTAPIEmployeeDetails(){
		List<Employee_Master> employee_masterList = null;
		try{
			ResponseEntity<Employee_Master[]> responseEntity = restTemplate.getForEntity("http://localhost:9000/rest-api-employee-services/getEmployeeDetails",Employee_Master[].class);
			employee_masterList = Arrays.asList(responseEntity.getBody());

		}catch(Exception e){
			e.printStackTrace();
		}
		return employee_masterList;
	}

	
	
	
}

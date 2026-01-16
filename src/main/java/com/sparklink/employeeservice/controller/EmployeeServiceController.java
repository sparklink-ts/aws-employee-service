package com.sparklink.employeeservice.controller;

import java.util.List;
import javax.transaction.Transactional;

import com.sparklink.employeeservice.exception.EmployeeInfoByIDNotFoundException;
import com.sparklink.employeeservice.model.*;
import com.sparklink.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = {"${settings.cors_origin}"})
//@CrossOrigin(origins = {"${settings.cors_origin_localhost}", "${settings.cors_origin_localhost_global}", "${settings.cors_origin}"})
@CrossOrigin(origins = {"${settings.cors_origin_localhost}", "${settings.cors_origin_localhost_global}", "${settings.cors_origin_client_global}","${settings.cors_origin_localhost_k8s}", "${settings.cors_origin_client_k8s}", "${settings.cors_origin_address_dynamic}", "${settings.cors_origin_address_dynamic_k8s}"})
@RestController
//@RequestMapping(value="/rest-api-employee-services")
@RequestMapping(value = {"${rest.request.mapping.rest-api-employee-services}"})
public class EmployeeServiceController {

	@Autowired
	private EmployeeService employeeService;


	//********************************* AWS Employee Service Blue Green Deployment Start *********************************************//

	//@GetMapping("/getEmployeeServicesDeployments")
	@GetMapping("${rest.get.mapping.getEmployeeServicesDeployments}")
	public String getEmployeeServicesDeployments() {
		return "AWS - Employee Services Working Fine ...";
	}

	@Transactional
	@Deprecated
	//@GetMapping("/getEmployeeDetails")
	@GetMapping("${rest.get.mapping.getEmployeeDetails}")
	public List<Employee_Master> getEmployeeDetails() {
		return employeeService.getEmployeeDetails();
	}

	//********************************* AWS Employee Service Blue Green Deployment Start *********************************************//


	//********************************* Employee_Master Start *********************************************//

	//@GetMapping("/getEmployeeServices")
	@GetMapping("${rest.get.mapping.getEmployeeServices}")
	public String getEmployeeServices() {
		return "AWS Employee Services Working Fine ...";
	}



	//@GetMapping("/callEmployeeServices")
	@GetMapping("${rest.get.mapping.callEmployeeServices}")
	public String callEmployeeServices() {
		return "Employee Services Working Fine ...";
	}

	// ****************** Calling from FrontController ********************** //

	@Transactional
	//@GetMapping("/getAllEmployee")
	@GetMapping("${rest.get.mapping.getAllEmployee}")
	public List<Employee_Master> getAllEmployee() {
		return employeeService.getAllEmployee();
	}


	@Transactional
	@Deprecated
	//@GetMapping("/getEmployeeCompleteDetails")
	@GetMapping("${rest.get.mapping.getEmployeeCompleteDetails}")
	public List<Employee_Complete_Details_View> getEmployeeCompleteDetails() {
		return employeeService.getEmployeeCompleteDetails();
	}

	@Transactional
	@Deprecated
	//@GetMapping("/getEmployeeDetailsById/{employeeId}")
	@GetMapping("${rest.get.mapping.getEmployeeDetailsById}")
	public Employee_Master getEmployeeDetailsById(@PathVariable int employeeId) {
		return employeeService.getEmployeeDetailsById(employeeId);
	}

	@Transactional
	//@PostMapping("/addEmployee")
	@PostMapping("${rest.post.mapping.addEmployeeDetails}")
	public Employee_Master addEmployeeDetails(@RequestBody Employee_Master employee) {
		return employeeService.addEmployeeDetails(employee);
	}

	@Transactional
	//@PutMapping("/updateEmployeeDetails")
	@PutMapping("${rest.put.mapping.updateEmployeeDetails}")
	public void updateEmployeeDetails(@RequestBody Employee_Master employee) {
		employeeService.updateEmployeeDetails(employee);
	}

	@Transactional
	//@PutMapping("/updateEmployeeDetailsById/{employeeId}")
	@PutMapping("${rest.put.mapping.updateEmployeeDetailsById}")
	public void updateEmployeeDetailsById(@RequestBody Employee_Master employee, @PathVariable int employeeId) {
		employeeService.updateEmployeeDetailsById(employee, employeeId);
	}

	@Transactional
	//@DeleteMapping("/deleteEmployeeDetails/{id}")
	//@GetMapping("/deleteEmployeeDetails/{employeeId}")
	@GetMapping("${rest.get.mapping.deleteEmployeeDetails}")
	public Boolean deleteEmployeeDetails(@PathVariable int employeeId) {
		return employeeService.deleteEmployeeDetails(employeeId);
	}

	//@GetMapping("/getDepartmentDetailsByEmployeeID/{employeeId}")
	@GetMapping("${rest.get.mapping.getDepartmentDetailsByEmployeeID}")
	@Deprecated
	public List<Employee_Master> getDepartmentDetailsByEmployeeID(@PathVariable int employeeId) {
		List<Employee_Master> employeeList= null;
		try {
			employeeList = employeeService.getDepartmentDetailsByEmployeeID(employeeId);
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {

		}
		return employeeList;
	}


	/*@Deprecated
	@GetMapping("/CallHCQLQueries")
	public List<Employee_Master> CallHCQLQueries(){
		return employeeService.CallHCQLQueries();
	}

	@GetMapping("/getRESTAPIEmployeeDetails")
	public List<Employee_Master> getRESTAPIEmployeeDetails(){
		return employeeService.getRESTAPIEmployeeDetails();
	}

	//@GetMapping("/getSubscriptionMessage")
	@GetMapping("${rest.get.mapping.getSubscriptionMessage}")
	public String getSubscriptionMessage(String user) {
		return employeeService.getSubscriptionMessage(user);
	}*/

	// ****************** @NamedQueries ********************** //

	@Transactional
	//@GetMapping("/getEmployeeDetailsByName/{employeeName}")
	@GetMapping("${rest.get.mapping.getEmployeeDetailsByName}")
	public List<Employee_Master> getEmployeeDetailsByName(@PathVariable String employeeName) {
		System.out.print("\n Employee name : " + employeeName + "\n");
		return employeeService.getEmployeeDetailsByName(employeeName.trim());
	}
	
	// ****************** @NamedQueries ********************** //

}

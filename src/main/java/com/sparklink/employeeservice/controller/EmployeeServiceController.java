package com.sparklink.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value="/rest-api-employee-services")
@RequestMapping(value = {"${rest.request.mapping.rest-api-employee-services}"})
public class EmployeeServiceController {

	//********************************* AWS Employee Service Blue Green Deployment Start *********************************************//

	//@GetMapping("/getEmployeeServicesDeployments")
	@GetMapping("${rest.get.mapping.getEmployeeServicesDeployments}")
	public String getEmployeeServicesDeployments() {
		return "AWS - Employee Services Working Fine ...";
	}

	//********************************* AWS Employee Service Blue Green Deployment Start *********************************************//




}

package com.sparklink.awsemployeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value="/rest-api-aws-employee-services")
@RequestMapping(value = {"${rest.request.mapping.rest-api-aws-employee-services}"})
public class AWSEmployeeServiceController {

	//********************************* AWS Employee Service Blue Green Deployment Start *********************************************//

	//@GetMapping("/getAWSEmployeeServicesDeployments")
	@GetMapping("${rest.get.mapping.getAWSEmployeeServicesDeployments}")
	public String getAWSEmployeeServicesDeployments() {
		return "AWS - Employee Services Working Fine on Elastic Beanstalk...";
	}

	//********************************* AWS Employee Service Blue Green Deployment Start *********************************************//




}

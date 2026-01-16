package com.sparklink.employeeservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/*
 * This is our model class and it corresponds to Employee table in database
 */

@NamedQueries(
	    {  
	        @NamedQuery(  
					        name = "@HQL_Find_Employee_By_Name",  
					        query = "from Employee_Master e where e.employeeName = :employeeName"
			),
	    }
	)
//select e.employeeId, e.employeeName,e.employeeSalary, e.employeeAge, e.employeeDesignation,e.employeeAddress,d.departmentName,d.departmentLocation from employee_master e, department_master d where e.employeeId = d.employeeId;

@Entity
@Table(name="employee_master")
public class Employee_Master {

	@Id
	@Column(name="employeeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;
	
	@Column(name="employeeName")
	private String employeeName;

	@Column(name="employeeSalary")
	private String employeeSalary;

	@Column(name="employeeAge")
	private int employeeAge;

	@Column(name="employeeDesignation")
	private String employeeDesignation;

	@Column(name="employeeAddress")
	private String employeeAddress;

	@Column(name="employeeType")
	private String employeeType;

	@Column(name="employeeGrade")
	private String employeeGrade;


	public Employee_Master() {
		super();
	}


	public Employee_Master(int employeeId, String employeeName, String employeeSalary, int employeeAge, String employeeDesignation, String employeeAddress, String employeeType, String employeeGrade) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeAge = employeeAge;
		this.employeeDesignation = employeeDesignation;
		this.employeeAddress = employeeAddress;
		this.employeeType = employeeType;
		this.employeeGrade = employeeGrade;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(String employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeGrade() {
		return employeeGrade;
	}

	public void setEmployeeGrade(String employeeGrade) {
		this.employeeGrade = employeeGrade;
	}


}

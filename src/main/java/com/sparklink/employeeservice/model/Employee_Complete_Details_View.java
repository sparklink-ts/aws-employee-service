package com.sparklink.employeeservice.model;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;*/


/*@NamedQueries(
		{
				@NamedQuery(
						name = "@HQL_Find_Employee_Complete_Details",
						query = "SELECT employeeId, employeeName, employeeSalary, employeeAge, employeeDesignation, employeeType, " +
								"employeeGrade, employeePermanentAddress, employeeAddressType, employeeDepartmentName, employeeDepartmentLocation " +
								"FROM Employee_Complete_Details_View"
				)

		}
)*/


/*@Entity
@Table(name="Employee_Complete_Details_View")*/
public class Employee_Complete_Details_View {

	//@Id
	//@Column(name="employeeId")
	private int employeeId;

	//@Column(name="employeeName")
	private String employeeName;

	//@Column(name="employeeSalary")
	private String employeeSalary;

	//@Column(name="employeeAge")
	private int employeeAge;

	//@Column(name="employeeDesignation")
	private String employeeDesignation;

	//@Column(name="employeeType")
	private String employeeType;

	//@Column(name="employeeGrade")
	private String employeeGrade;

	//@Column(name="employeePermanentAddress")
	private String employeePermanentAddress;

	//@Column(name="employeeCurrentAddress")
	private String employeeCurrentAddress;

	//@Column(name="employeeAddressType")
	private String employeeAddressType;

	//@Column(name="employeeDepartmentName")
	private String employeeDepartmentName;

	//@Column(name="employeeDepartmentLocation")
	private String employeeDepartmentLocation;

	public Employee_Complete_Details_View() {
		super();
	}

	public Employee_Complete_Details_View(int employeeId, String employeeName, String employeeSalary, int employeeAge, String employeeDesignation, String employeeType, String employeeGrade, String employeePermanentAddress, String employeeCurrentAddress, String employeeAddressType, String employeeDepartmentName, String employeeDepartmentLocation) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeAge = employeeAge;
		this.employeeDesignation = employeeDesignation;
		this.employeeType = employeeType;
		this.employeeGrade = employeeGrade;
		this.employeePermanentAddress = employeePermanentAddress;
		this.employeeCurrentAddress = employeeCurrentAddress;
		this.employeeAddressType = employeeAddressType;
		this.employeeDepartmentName = employeeDepartmentName;
		this.employeeDepartmentLocation = employeeDepartmentLocation;
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

	public String getEmployeeType() {return employeeType;}

	public void setEmployeeType(String employeeType) {this.employeeType = employeeType;	}

	public String getEmployeeGrade() {return employeeGrade;	}

	public void setEmployeeGrade(String employeeGrade) {this.employeeGrade = employeeGrade;	}

	public String getEmployeePermanentAddress() {
		return employeePermanentAddress;
	}

	public void setEmployeePermanentAddress(String employeePermanentAddress) { this.employeePermanentAddress = employeePermanentAddress; }

	public String getEmployeeCurrentAddress() {	return employeeCurrentAddress;	}

	public void setEmployeeCurrentAddress(String employeeCurrentAddress) { this.employeeCurrentAddress = employeeCurrentAddress; }

	public String getEmployeeAddressType() {return employeeAddressType;	}

	public void setEmployeeAddressType(String employeeAddressType) {this.employeeAddressType = employeeAddressType;	}

	public String getEmployeeDepartmentName() {
		return employeeDepartmentName;
	}

	public void setEmployeeDepartmentName(String employeeDepartmentName) {this.employeeDepartmentName = employeeDepartmentName;	}

	public String getEmployeeDepartmentLocation() {
		return employeeDepartmentLocation;
	}

	public void setEmployeeDepartmentLocation(String employeeDepartmentLocation) {this.employeeDepartmentLocation = employeeDepartmentLocation;	}



}

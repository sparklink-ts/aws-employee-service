package com.sparklink.employeeservice.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.sparklink.employeeservice.exception.EmployeeInfoByIDNotFoundException;
import com.sparklink.employeeservice.exception.EmployeeInfoByNameNotFoundException;
import com.sparklink.employeeservice.exception.NoEmployeeDataFoundException;
import com.sparklink.employeeservice.exception.RecordNotFoundNullPointerException;
import com.sparklink.employeeservice.model.Employee_Complete_Details_View;
import com.sparklink.employeeservice.model.Employee_Master;


import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository")
public class EmployeeRepository {

	@Autowired
	private SessionFactory sessionFactory;
	private static volatile Session session = null;

	private String message ="";
	private Boolean matched = false;

	private List<Employee_Master> employeeList;
	private List<Employee_Complete_Details_View> employeeCompleteDetailsViewList = null;
	private Criteria criteria;


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	// ****************** Calling from FrontController - Start - ********************** //

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Employee_Master> getAllEmployee() {
		//Session session = this.sessionFactory.getCurrentSession();
		employeeList = new ArrayList<>();
		try {
				//Using HQL
				//employeeList = session.createQuery("from Employee_Master order by employeeName").list();
				employeeList = getCurrentSession().createQuery("from Employee_Master order by employeeId").list();
				if(employeeList.isEmpty())
					System.out.println("employeeList is empty....");
				else
					System.out.println("employeeList is not empty....Size : " + employeeList.size());
		}catch(NoEmployeeDataFoundException ndf) {
			ndf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			
		}
		return employeeList;
	}

	@Transactional
	@Deprecated
	public List<Employee_Master> getEmployeeDetails(){
		try {
				employeeList = new ArrayList<>();

				//Using HCQL, Order Class
				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.addOrder(Order.asc("employeeId"));
				employeeList = criteria.list();
		}catch(NoEmployeeDataFoundException ndf) {
			ndf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}catch(Exception ex){
			 ex.printStackTrace();
		}
		finally {

		}
		return employeeList ;
	}


	@SuppressWarnings("unchecked")
	@Transactional
	@Deprecated
	public List<Employee_Complete_Details_View> getEmployeeCompleteDetails() {
		//Session session = this.sessionFactory.getCurrentSession();
		employeeCompleteDetailsViewList = new ArrayList<>();
		try {
				//Using HQL
				//employeeCompleteDetailsViewList = session.createQuery("from Employee_Complete_Details_View").list();
				//employeeCompleteDetailsViewList = getCurrentSession().createQuery("from Employee_Complete_Details_View").list();

				//Using HCQL, Order Class
				criteria = getCurrentSession().createCriteria(Employee_Complete_Details_View.class);
				criteria.addOrder(Order.asc("employeeId"));
				employeeCompleteDetailsViewList = criteria.list();

				if(employeeCompleteDetailsViewList.isEmpty())
					System.out.println("employeeCompleteDetailsViewList is empty....");
				else
					System.out.println("employeeCompleteDetailsViewList is not empty....Size : " + employeeCompleteDetailsViewList.size());
		}catch(NoEmployeeDataFoundException ndf) {
			ndf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {

		}
		return employeeCompleteDetailsViewList;
	}


	@Transactional
	@Deprecated
	public Employee_Master getEmployeeDetailsById(int employeeId) {
		//Session session = this.sessionFactory.getCurrentSession();
		Employee_Master employee = null;
		try {
				//Using HQL
				//employee = session.get(Employee_Master.class, employeeId);
				//employee = getCurrentSession().get(Employee_Master.class, employeeId);

				//Using HCQL, Restriction Class
				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.eq("employeeId",Integer.valueOf(employeeId)));
				//employee = (Employee_Master) criteria.list().get(0);
				employee = (Employee_Master) criteria.uniqueResult();
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}catch(RecordNotFoundNullPointerException rnfnpe) {
			rnfnpe.printStackTrace();
		}finally {
			
		}
		return employee;
	}

	@Transactional
	public Employee_Master addEmployeeDetails(Employee_Master employee) {
		//Session session = this.sessionFactory.getCurrentSession();
		try {
				//Using HQL
				//Serializable savedEmployeeId = session.save(employee);
				Serializable savedEmployeeId = getCurrentSession().save(employee);
				if(savedEmployeeId != null)
					System.out.println("Saved Employee ID :: " + savedEmployeeId);
				else
					System.out.println("Employee Record Not Saved... ");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Transactional
	public void updateEmployeeDetails(Employee_Master employee) {
		//Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(employee);
		//Using HQL
		//session.update(employee);
		getCurrentSession().update(employee);
	}

	@Transactional
	public void updateEmployeeDetailsById(Employee_Master employee, int employeeId) {
		//Session session = this.sessionFactory.getCurrentSession();
		Hibernate.initialize(employee);
		//Using HQL
		//session.saveOrUpdate(String.valueOf(employeeId), employee);
		getCurrentSession().saveOrUpdate(String.valueOf(employeeId), employee);
	}
	
	@Transactional
	public Boolean deleteEmployeeDetails(int employeeId) {
		System.out.println("deleteEmployee :: " + employeeId);
		//Session session = this.sessionFactory.getCurrentSession();
		//Transaction transaction = session.beginTransaction();
		//Using HQL
		//Employee_Master employee_master = (Employee_Master) session.get(Employee_Master.class, employeeId);
		Employee_Master employee_master = (Employee_Master) getCurrentSession().get(Employee_Master.class, employeeId);
		try {
			if (employee_master != null) {
				matched = true;
				System.out.println(employee_master.getEmployeeId());
				//Using HQL
				session.delete(employee_master);
				message = "Deleted Employee Record is:" + " \n" + "Employee ID: " + employee_master.getEmployeeId() + "  Employee Name: " + employee_master.getEmployeeName() + "  Employee Salary: " + employee_master.getEmployeeSalary() + "  Employee Age: " + employee_master.getEmployeeAge() + "  Employee Designation: " + employee_master.getEmployeeDesignation() + "\n"; 
			}else{
				matched = false;
				message = "Employee with ID : " + employeeId + " not found....";
			}
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {
			//transaction.commit();
		}
		
		System.out.println("Delete message :: " + message);
		return matched;
	}


	@SuppressWarnings("unchecked")
	@Transactional
	@Deprecated
	public List<Employee_Master> getDepartmentDetailsByEmployeeID(int employeeId) {
		//Session session = this.sessionFactory.getCurrentSession();
		List<Employee_Master> employeeList= null;
		//Employee_Master employee = null;
		try {
				//employee = session.get(Employee_Master.class, employeeId);

				//Using HCQL and Restriction class
				//Criteria criteria = session.createCriteria(Employee_Master.class);
				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.gt("employeeSalary", String.valueOf(employeeId)));//salary is the property name
				employeeList = criteria.list();
		}catch(EmployeeInfoByIDNotFoundException enf) {
			enf.printStackTrace();
		}finally {

		}
		return employeeList;
	}

	// ****************** @NamedQueries - Start - ********************** //

		@SuppressWarnings("unchecked")
		@Transactional
		@Deprecated
		public List<Employee_Master> getEmployeeDetailsByName(String employeeName) {
			//Session session = this.sessionFactory.getCurrentSession();
			Query query = null;
			//Optional<Employee> employeeOptional = null;
			List<Employee_Master> employeeList= null;
			
			try {
					//Using HQL and Named Queries
					//query = session.getNamedQuery("@HQL_Find_Employee_By_Name");
				    query = getCurrentSession().getNamedQuery("@HQL_Find_Employee_By_Name");

					query.setParameter("employeeName", employeeName);
					System.out.println("Named Query is : " + query.getQueryString());
					
					employeeList = (List<Employee_Master>) query.list().stream().collect(Collectors.toList());
					//employeeList = session.createQuery(query.toString()).list();
					
					if(employeeList.isEmpty())
						employeeList = null;
			}catch(EmployeeInfoByNameNotFoundException enfbn) {
				enfbn.printStackTrace();
			}finally {
				
			}
			return employeeList;
		}

	// ****************** @NamedQueries - Stop - ********************** //

/*
		@Transactional
		@Deprecated
		public List<Employee_Master> CallHCQLQueries(){
			try{
				// Order class
				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.addOrder(Order.asc("employeeName"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Employee Name in Asc Order : " + n.getEmployeeName()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.addOrder(Order.desc("employeeName"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Employee Name in Desc Order : " + n.getEmployeeName()));


				// Restriction class
				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.lt("employeeSalary", "100000"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Salary < 100000  Employee Name : " + n.getEmployeeName() + " <---> Employee Salary : " + n.getEmployeeSalary()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.le("employeeSalary", "100000"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Salary <= 100000  Employee Name : " + n.getEmployeeName() + " <---> Employee Salary : " + n.getEmployeeSalary()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.gt("employeeSalary", "100000"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Salary > 100000  Employee Name : " + n.getEmployeeName() + " <---> Employee Salary : " + n.getEmployeeSalary()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.ge("employeeSalary", "100000"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Salary >= 100000  Employee Name : " + n.getEmployeeName() + " <---> Employee Salary : " + n.getEmployeeSalary()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.eq("employeeSalary", "100000"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Salary = 100000  Employee Name : " + n.getEmployeeName() + " <---> Employee Salary : " + n.getEmployeeSalary()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.ne("employeeSalary", "100000"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Salary = 100000  Employee Name : " + n.getEmployeeName() + " <---> Employee Salary : " + n.getEmployeeSalary()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.between("employeeSalary", "50000", "200000"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Salary between 50000 and 200000  Employee Name : " + n.getEmployeeName() + " <---> Employee Salary : " + n.getEmployeeSalary()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.like("employeeName","%Mi%"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Employee Name's like %Mi%      Employee Name : " + n.getEmployeeName()));

				criteria = getCurrentSession().createCriteria(Employee_Master.class);
				criteria.add(Restrictions.ilike("employeeName","%mith%"));
				employeeList = criteria.list();
				employeeList.stream().forEach(n->System.out.println("Employee Name iLike mith%: " + n.getEmployeeName()));

			}catch (Exception e){
				e.printStackTrace();
			}

			return employeeList;
		}*/

	// ****************** @NamedQueries - End - ********************** //


	public Session getCurrentSession(){
		if(session == null) {
			session = this.sessionFactory.getCurrentSession();
		}else{
			if(!session.isOpen())
				session = this.sessionFactory.getCurrentSession();
		}
		return session;
	}

}

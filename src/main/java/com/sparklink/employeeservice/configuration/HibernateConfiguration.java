package com.sparklink.employeeservice.configuration;

import java.util.Properties;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConfigurationProperties("springboot.datasource")
@EnableTransactionManagement
public class HibernateConfiguration {

	Logger logger = LoggerFactory.getLogger("Employee Service HibernateConfiguration");


	// Local Database Properties
	@Value("${springboot.database_name}")
	private String database_name;

	@Value("${springboot.datasource.url}")
	private String URL;

	@Value("${springboot.datasource.driver_class_name}")
	private String DRIVER_CLASS_NAME;

	@Value("${springboot.datasource.password}")
	private String PASSWORD;

	@Value("${springboot.datasource.username}")
	private String USERNAME;


	
	//Local Hibernate Properties
	@Value("${springboot.hibernate.dialect}")
	private String DIALECT;

	@Value("${springboot.hibernate.show_sql}")
	private String SHOW_SQL;

	@Value("${springboot.hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${springboot.entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;
	

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		//logger.info(" ::::  DB URL  ::::   " + URL);
		dataSource.setUrl(URL);
		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);

		System.out.println("DB Url : " + dataSource.getUrl());
		System.out.println("Database_name : " + database_name);
		System.out.println("DB Username : " + dataSource.getUsername());

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		Properties hibernateProperties = new Properties();

		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);

		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}


	@Profile("dev")
	@Bean
	public String devDatabaseConnection(){
		System.out.print("DB Connection to MySQL_DEV - High Performance Instance..");
		System.out.print(DRIVER_CLASS_NAME);
		System.out.print(URL);
		return "DB Connection to MySQL_DEV - High Performance Instance..";
	}

	@Profile("qa")
	@Bean
	public String qaDatabaseConnection(){
		System.out.print("DB Connection to MySQL_QA - High Performance Instance..");
		System.out.print(DRIVER_CLASS_NAME);
		System.out.print(URL);
		return "DB Connection to MySQL_QA - High Performance Instance..";
	}

	@Profile("stage")
	@Bean
	public String stageDatabaseConnection(){
		System.out.print("DB Connection to MySQL_STAGE - High Performance Instance..");
		System.out.print(DRIVER_CLASS_NAME);
		System.out.print(URL);
		return "DB Connection to MySQL_STAGE - High Performance Instance..";
	}

	@Profile("prod")
	@Bean
	public String prodDatabaseConnection(){
		System.out.print("DB Connection to MySQL_PROD - High Performance Instance..");
		System.out.print(DRIVER_CLASS_NAME);
		System.out.print(URL);
		return "DB Connection to MySQL_PROD - High Performance Instance..";
	}

	@Profile("uat")
	@Bean
	public String uatDatabaseConnection(){
		System.out.print("DB Connection to MySQL_UAT - High Performance Instance..");
		System.out.print(DRIVER_CLASS_NAME);
		System.out.print(URL);
		return "DB Connection to MySQL_UAT - High Performance Instance..";
	}



}
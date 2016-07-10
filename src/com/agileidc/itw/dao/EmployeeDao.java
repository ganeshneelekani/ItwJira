package com.agileidc.itw.dao;

import java.util.List;

import com.agileidc.itw.model.Employee;

public interface EmployeeDao {
	
	public void addEmployee(Employee employee);

	public List<Employee> listEmployeess();
	
	public Employee getEmployee(int empid);
	
	public void deleteEmployee(Employee employee);
}

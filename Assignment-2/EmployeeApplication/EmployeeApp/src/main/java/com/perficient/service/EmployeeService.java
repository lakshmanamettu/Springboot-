package com.perficient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.Entity.Employee;
import com.perficient.repository.EmployeeRepository;
import com.perfiecient.Exception.BadRequestException;
import com.perfiecient.Exception.EmployeeNotFoundException;
import com.perfiecient.Exception.ServiceException;

/**
 * The Class EmployeeService.
 */
@Service
public class EmployeeService {
	
	/** The employee repository. */
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * Fetch employee details.
	 *
	 * @return the list
	 */
	public List<Employee> fetchEmployeeDetails(){
		
		List<Employee> employee = null;
		
		employee = employeeRepository.findAll();
		if(employee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee Details are not present in DB ");
		}else {
			return employee;
	}
		
	}
	
	/**
	 * Save employee details.
	 *
	 * @param employee the employee
	 */
	public void saveEmployeeDetails(Employee employee) {
		 checkForEmployeeObject(employee);
		 try {
			employeeRepository.save(employee);
		} catch (Exception e) {
			throw new ServiceException("Exception Occurs While Saving the details in Database");
		}
	}
	
	/**
	 * Fetch employee details by employee id.
	 *
	 * @param Id the id
	 * @return the employee
	 */
	public Employee fetchEmployeeDetailsByEmployeeId(Long Id) {
		Optional<Employee> emp = null;
		
		emp = employeeRepository.findById(Id);
		if(emp.isPresent()) {
			return emp.get();
		}else {
			throw new EmployeeNotFoundException("Employee Details are not present in DB");
		}	
	}
	
	/**
	 * Update employee details.
	 *
	 * @param employee the employee
	 */
	public void updateEmployeeDetails(Employee employee) {
		checkForEmployeeId(employee);
		 try {
			employeeRepository.save(employee);
		} catch (Exception e) {
			throw new ServiceException("Exception Occurs While Updating the details in Database");
		}
	}
	
	/**
	 * Delete employee details.
	 *
	 * @param Id the id
	 */
	public void deleteEmployeeDetails(Long Id) {
		
		employeeRepository.deleteById(Id);
	}
	
	/**
     * Check for employee id.
     *
     * @param employee the employee
     */
    private void checkForEmployeeId(Employee employee) {
		if(employee.getId()==null ) {
			throw new BadRequestException("Please Provide the Valid EmployeeId to update the details");
		}
		
	}
	 /**
     * Check for employee object.
     *
     * @param employee the employee
     */
    private void checkForEmployeeObject(Employee employee) {
		
		if(employee.getFirstName() == null || employee.getLastName() == null 
				|| employee.getEmail() == null) {			
			throw new BadRequestException("Invalid Employee Object");			
		}
		
	}

}

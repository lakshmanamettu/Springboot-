package com.perficient.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.hamcrest.core.IsNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.MediaType;
import com.perficient.Entity.Employee;
import com.perficient.repository.EmployeeRepository;
import com.perficient.service.EmployeeService;
import com.perfiecient.Exception.BadRequestException;
import com.perfiecient.Exception.EmployeeNotFoundException;
import com.perfiecient.Exception.ServiceException;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeController.
 */
@RestController
public class EmployeeController {
	
	
	/** The employee service. */
	@Autowired
	EmployeeService employeeService;
	
	
	/**
	 * Gets the employee details.
	 *
	 * @return the employee details
	 */
	@GetMapping(value="/employes")
	public List<Employee> getEmployeeDetails(){
		
		List<Employee> employeeList = employeeService.fetchEmployeeDetails();
		return employeeList;						
	}
	
	
	/**
	 * Creates the employee deatils.
	 *
	 * @param employee the employee
	 */
	@PostMapping(value="/employes")
	public void createEmployeeDeatils(@RequestBody Employee employee){
			
		employeeService.saveEmployeeDetails(employee);
		
	}

		
	/**
	 * Gets the employee details by employee id.
	 *
	 * @param Id the id
	 * @return the employee details by employee id
	 */
	@GetMapping(value="/employes/{Id}")
	public Employee getEmployeeDetailsByEmployeeId(@PathVariable Long Id){
		
		Employee employee = employeeService.fetchEmployeeDetailsByEmployeeId(Id);
		
		return employee;
		} 

	
	/**
	 * Update employee details.
	 *
	 * @param employee the employee
	 */
	@PutMapping(value="/employes")
	public void updateEmployeeDetails(@RequestBody Employee employee){
		
		employeeService.updateEmployeeDetails(employee);
		 
	}

	
	/**
	 * Delete employee details by employee id.
	 *
	 * @param Id the id
	 */
	@DeleteMapping(value="/employes/{Id}")
	public void deleteEmployeeDetailsByEmployeeId(@PathVariable Long Id) {
		
		employeeService.deleteEmployeeDetails(Id);	
	}
	
    
     
     
}

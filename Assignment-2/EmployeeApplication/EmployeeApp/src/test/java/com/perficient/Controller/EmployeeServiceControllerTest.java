package com.perficient.Controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.perficient.Entity.Address;
import com.perficient.Entity.Employee;
import com.perficient.Entity.Field;
import com.perficient.Entity.Skills;
import com.perficient.controller.EmployeeController;
import com.perficient.repository.EmployeeRepository;
import com.perficient.service.EmployeeService;
import com.perfiecient.Exception.EmployeeNotFoundException;
import com.perfiecient.Exception.ServiceException;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceControllerTest {
	
	@Before
	public void setup() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	
	/**
	 * @throws EmployeeNotFoundException
	 */
	@Test
	public void getEmployeeDetails() throws EmployeeNotFoundException {
		
		List<Employee> employee = new ArrayList<>();
		Employee emp = generateEmployeeDetails();
		employee.add(emp);
		Mockito.when(employeeService.fetchEmployeeDetails()).thenReturn(employee);
		List<Employee> empList = employeeController.getEmployeeDetails();
		assertEquals("EmailId is :", "anvesh@gmail.com", empList.get(0).getEmail());
		assertEquals("FirstName is :", "Anvesh", empList.get(0).getFirstName());
		assertEquals("EmailId is :", "Kansas City", empList.get(0).getAddress().getCity());
		assertEquals("EmailId is :", "working as PCF Developer", empList.get(0).getSkills().get(0).getExperience());	
		
		Mockito.verify(employeeService).fetchEmployeeDetails();
		
	}
	@Test
	public void createEmployeeDetails() {
		Employee emp = generateEmployeeDetails();
		Mockito.doNothing().when(employeeService).saveEmployeeDetails(emp);
		employeeController.createEmployeeDeatils(emp);
	
		Mockito.verify(employeeService).saveEmployeeDetails(emp);
				
	}
	
	@Test
	public void updateEmployeeDetails() {
		Employee emp = generateEmployeeDetails();
		Mockito.doNothing().when(employeeService).updateEmployeeDetails(emp);
		employeeController.updateEmployeeDetails(emp);;
		assertEquals("EmployeeId is :", new Long(1), emp.getId());
		assertEquals("EmailId is :", "Kansas City", emp.getAddress().getCity());
		assertEquals("EmailId is :", "working as PCF Developer", emp.getSkills().get(0).getExperience());	
		Mockito.verify(employeeService).updateEmployeeDetails(emp);
				
	}
	
	@Test
	public void deleteEmployeeDetails() {
		Employee emp = generateEmployeeDetails();
		Mockito.doNothing().when(employeeService).deleteEmployeeDetails(emp.getId());
		employeeController.deleteEmployeeDetailsByEmployeeId(emp.getId());
		assertEquals("EmployeeId is :", new Long(1), emp.getId());
		assertEquals("EmailId is :", "anvesh@gmail.com", emp.getEmail());	
		Mockito.verify(employeeService).deleteEmployeeDetails(emp.getId());
				
	}
	
	@Test
	public void getEmployeeDetailsById() throws ServiceException, EmployeeNotFoundException {			
		Employee emp = generateEmployeeDetails();
		Mockito.when(employeeService.fetchEmployeeDetailsByEmployeeId(emp.getId())).thenReturn(emp);
		Employee employee = employeeController.getEmployeeDetailsByEmployeeId(emp.getId());
		assertEquals("EmployeeId is :", new Long(1), employee.getId());
		assertEquals("EmailId is :", "anvesh@gmail.com", employee.getEmail());
		assertEquals("FirstName is :", "Anvesh", employee.getFirstName());
		assertEquals("EmailId is :", "Kansas City", employee.getAddress().getCity());
		assertEquals("EmailId is :", "working as PCF Developer", employee.getSkills().get(0).getExperience());	
		Mockito.verify(employeeService).fetchEmployeeDetailsByEmployeeId(emp.getId());
				
	}
	
	public Employee generateEmployeeDetails() {
		Employee emp = new Employee();
		emp.setId(new Long(1));
		emp.setEmail("anvesh@gmail.com");
		emp.setFirstName("Anvesh");
		emp.setLastName("Mandadi");
		Address address = new Address();
		address.setCity("Kansas City");
		address.setPostal("postal");
		emp.setAddress(address);
		List<Skills> skill = new ArrayList<>();
		Skills skills = new Skills();
		skills.setExperience("working as PCF Developer");
		Field field = new Field();
		field.setName("Software Engineer");
		skills.setField(field);
		skill.add(skills);
		emp.setSkills(skill);
		return emp;
	}
}

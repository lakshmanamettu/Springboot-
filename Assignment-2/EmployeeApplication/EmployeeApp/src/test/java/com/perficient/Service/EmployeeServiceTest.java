package com.perficient.Service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.perficient.Entity.Address;
import com.perficient.Entity.Employee;
import com.perficient.Entity.Field;
import com.perficient.Entity.Skills;
import com.perficient.repository.EmployeeRepository;
import com.perficient.service.EmployeeService;
import com.perfiecient.Exception.EmployeeNotFoundException;
import com.perfiecient.Exception.ServiceException;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	
	@Before
	public void setup() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	
	/**
	 * @throws EmployeeNotFoundException
	 */
	@Test
	public void getEmployeeDetails() {
		
		List<Employee> employee = new ArrayList<>();
		Employee emp = generateEmployeeDetails();
		employee.add(emp);
		Mockito.when(employeeRepository.findAll()).thenReturn(employee);
		List<Employee> empList = employeeService.fetchEmployeeDetails();
		assertEquals("EmailId is :", "anvesh@gmail.com", empList.get(0).getEmail());
		assertEquals("FirstName is :", "Anvesh", empList.get(0).getFirstName());
		assertEquals("EmailId is :", "Kansas City", empList.get(0).getAddress().getCity());
		assertEquals("EmailId is :", "working as PCF Developer", empList.get(0).getSkills().get(0).getExperience());	
		
		Mockito.verify(employeeRepository).findAll();
		
	}
	
	@Test(expected=EmployeeNotFoundException.class)
	public void getEmployeeDetailsForBadRequest() {
		
		List<Employee> employee = new ArrayList<>();
		Employee emp = new Employee();
		employee.add(emp);
		Mockito.when(employeeRepository.findAll()).
		            thenThrow(new EmployeeNotFoundException("Employee Details are not present in DB"));
		List<Employee> empList = employeeService.fetchEmployeeDetails();		
		Mockito.verify(employeeRepository).findAll();
		
	}
	
	@Test
	public void createEmployeeDetails() {
		Employee emp = generateEmployeeDetails();
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
		employeeService.saveEmployeeDetails(emp);
		assertEquals("EmailId is :", "anvesh@gmail.com", emp.getEmail());
		assertEquals("FirstName is :", "Anvesh", emp.getFirstName());
	
		Mockito.verify(employeeRepository).save(emp);
				
	}
	
	@Test(expected=ServiceException.class)
	public void createEmployeeDetailsForServiceException(){
		Employee emp = generateEmployeeDetails();
		Mockito.when(employeeRepository.save(emp)).thenThrow(ServiceException.class);
		employeeService.saveEmployeeDetails(emp);	
		Mockito.verify(employeeRepository).save(emp);
				
	}
	
	@Test
	public void updateEmployeeDetails() {
		Employee emp = generateEmployeeDetails();
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
		employeeService.updateEmployeeDetails(emp);;
		assertEquals("EmployeeId is :", new Long(1), emp.getId());
		assertEquals("EmailId is :", "Kansas City", emp.getAddress().getCity());
		assertEquals("EmailId is :", "working as PCF Developer", emp.getSkills().get(0).getExperience());	
		Mockito.verify(employeeRepository).save(emp);
				
	}
	
	@Test(expected=ServiceException.class)
	public void updateEmployeeDetailsForServiceException(){
		Employee emp = generateEmployeeDetails();
		Mockito.when(employeeRepository.save(emp)).thenThrow(ServiceException.class);
		employeeService.updateEmployeeDetails(emp);	
		Mockito.verify(employeeRepository).save(emp);
				
	}
	
	@Test
	public void deleteEmployeeDetails() {
		Employee emp = generateEmployeeDetails();
		Mockito.doNothing().when(employeeRepository).deleteById(emp.getId());
		employeeService.deleteEmployeeDetails(emp.getId());
		assertEquals("EmployeeId is :", new Long(1), emp.getId());
		assertEquals("EmailId is :", "anvesh@gmail.com", emp.getEmail());	
		Mockito.verify(employeeRepository).deleteById(emp.getId());
				
	}
	
	@Test
	public void getEmployeeDetailsById() throws ServiceException, EmployeeNotFoundException {			
		Employee emp = generateEmployeeDetails();
		Mockito.when(employeeRepository.findById(emp.getId())).thenReturn(Optional.ofNullable(emp));
		Employee employee = employeeService.fetchEmployeeDetailsByEmployeeId(emp.getId());
		assertEquals("EmployeeId is :", new Long(1), employee.getId());
		assertEquals("EmailId is :", "anvesh@gmail.com", employee.getEmail());
		assertEquals("FirstName is :", "Anvesh", employee.getFirstName());
		assertEquals("EmailId is :", "Kansas City", employee.getAddress().getCity());
		assertEquals("EmailId is :", "working as PCF Developer", employee.getSkills().get(0).getExperience());	
		Mockito.verify(employeeRepository).findById(employee.getId());
				
	}
	
	@Test(expected=EmployeeNotFoundException.class)
	public void getEmployeeDetailsByIdForBadRequest() {
		
		List<Employee> employee = new ArrayList<>();
		Employee emp = new Employee();
		employee.add(emp);
		Mockito.when(employeeRepository.findById(emp.getId())).
		            thenThrow(new EmployeeNotFoundException("Employee Details are not present in DB for EmployeeId"));
		Employee emp1 = employeeService.fetchEmployeeDetailsByEmployeeId(emp.getId());		
		Mockito.verify(employeeRepository).findById(emp.getId());
		
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

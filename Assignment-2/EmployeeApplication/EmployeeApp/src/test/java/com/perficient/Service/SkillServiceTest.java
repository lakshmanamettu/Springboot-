package com.perficient.Service;

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
import com.perficient.repository.EmployeeRepository;
import com.perficient.repository.SkillRepository;
import com.perficient.service.SkillService;
import com.perfiecient.Exception.BadRequestException;
import com.perfiecient.Exception.EmployeeNotFoundException;
import com.perfiecient.Exception.ServiceException;

/**
 * The Class SkillServiceTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {
	
	/**
	 * Setup.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setup() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	/** The skill repository. */
	@Mock
	private SkillRepository skillRepository;
	
	/** The employee repository. */
	@Mock
	private EmployeeRepository employeeRepository;
	
	/** The skill service. */
	@InjectMocks
	private SkillService skillService;
	
	/**
	 * Adds the skills to employee.
	 */
	@Test
	public void addSkillsToEmployee() {
		Skills skill = getSkillsObject();
		Mockito.when(skillRepository.save(skill)).thenReturn(skill);
		skillService.addSkillsToEmployee(skill);
		Mockito.verify(skillRepository).save(skill);
	}
	
	/**
	 * Update technical skills.
	 */
	@Test
	public void updateTechnicalSkills() {
		Skills skill = getSkillsObject();
		Mockito.when(skillRepository.save(skill)).thenReturn(skill);
		skillService.updateEmployeeSkills(skill);
		Mockito.verify(skillRepository).save(skill);
	}
	
	/**
	 * Delete employee skills by id.
	 */
	@Test
	public void deleteEmployeeSkillsById() {
		Skills skill = getSkillsObject();
		Mockito.doNothing().when(skillRepository).deleteById(skill.getId());
		skillService.deleteEmployeeSkills(skill.getId());
		Mockito.verify(skillRepository).deleteById(skill.getId());
	}

	/**
	 * Gets the employee skills by id.
	 *
	 * @return the employee skills by id
	 */
	@Test
	public void getEmployeeSkillsById() {
		Skills skill = getSkillsObject();
		Mockito.when(skillRepository.findByEmployeeIdAndSkillId
				(skill.getEmployee_Id(), skill.getId())).thenReturn(Optional.ofNullable(skill));
		Skills skills = skillService.retriveSkillsByEmployeeId(skill.getEmployee_Id(), skill.getId());
		assertEquals("EmployeeId is :", new Long(1), skills.getEmployee_Id());
		assertEquals("SkillId is :", new Long(2), skills.getId());
		assertEquals("FieldId is :", new Long(3), skills.getField().getId());
		assertEquals("Summary is :", "Working as PCF Developer", skills.getSummary());
		Mockito.verify(skillRepository).findByEmployeeIdAndSkillId(skill.getEmployee_Id(), skill.getId());
	}
	
	/**
	 * Gets the employee skills by employee id.
	 *
	 * @return the employee skills by employee id
	 */
	@Test
	public void getEmployeeSkillsByEmployeeId() {
		Employee employee = generateEmployeeDetails();
		Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.ofNullable(employee));
		List<Skills> skills = skillService.retriveEmployeeSklillsByEmployeeId(employee.getId());
		assertEquals("EmployeeId is :", new Long(1), skills.get(0).getEmployee_Id());
		assertEquals("SkillId is :", new Long(2), skills.get(0).getId());
		assertEquals("FieldId is :", new Long(3), skills.get(0).getField().getId());
		assertEquals("Summary is :", "Working as PCF Developer", skills.get(0).getSummary());
		Mockito.verify(employeeRepository).findById(employee.getId());
	}
	
	/**
	 * Gets the skills object.
	 *
	 * @return the skills object
	 */
	public Skills getSkillsObject() {
		Skills skill = new Skills();
		skill.setEmployee_Id(new Long(1));
		skill.setExperience("experience");
		skill.setId(new Long(2));
		skill.setSummary("Working as PCF Developer");
		
		Field field = new Field();
		field.setId(new Long(3));
		field.setName("Java");
		field.setType("Software Engineer");
		skill.setField(field);
		
		return skill;
	}
	
	/**
	 * Generate employee details.
	 *
	 * @return the employee
	 */
	public Employee generateEmployeeDetails() {
		Employee emp = new Employee();
		emp.setId(new Long(1));
		emp.setEmail("anvesh@gmail.com");
		emp.setFirstName("Anvesh");
		Address address = new Address();
		address.setCity("Kansas City");
		address.setPostal("postal");
		emp.setAddress(address);
		List<Skills> skill = new ArrayList<>();
		Skills skills = getSkillsObject();		
		skill.add(skills);
		emp.setSkills(skill);
		return emp;
	}
}

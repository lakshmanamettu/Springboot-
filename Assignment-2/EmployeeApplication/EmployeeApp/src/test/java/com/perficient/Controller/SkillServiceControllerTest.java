package com.perficient.Controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.perficient.Entity.Field;
import com.perficient.Entity.Skills;
import com.perficient.controller.SkillController;
import com.perficient.service.SkillService;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceControllerTest {
	
	
	@Mock
	private SkillService skillService;
	
	@InjectMocks
	private SkillController skillController;
	
	@Test
	public void addSkillsToEmployee() {
		Skills skill = getSkillsObject();
		Mockito.doNothing().when(skillService).addSkillsToEmployee(skill);
		skillController.addSkillsToEmployee(skill);
		Mockito.verify(skillService).addSkillsToEmployee(skill);
	}
	
	@Test
	public void updateTechnicalSkills() {
		Skills skill = getSkillsObject();
		Mockito.doNothing().when(skillService).updateEmployeeSkills(skill);
		skillController.updateTechnicalSkillsByEmployeeId(skill);
		Mockito.verify(skillService).updateEmployeeSkills(skill);
	}
	
	@Test
	public void deleteEmployeeSkillsById() {
		Skills skill = getSkillsObject();
		Mockito.doNothing().when(skillService).deleteEmployeeSkills(skill.getId());
		skillController.deleteEmployeeSkillsById(skill.getId());
		Mockito.verify(skillService).deleteEmployeeSkills(skill.getId());
	}
	
	@Test
	public void getEmployeeSkillsById() {
		Skills skill = getSkillsObject();
		Mockito.when(skillService.retriveSkillsByEmployeeId
				(skill.getEmployee_Id(), skill.getId())).thenReturn(skill);
		Skills skills = skillController.getSkillByEmployeeId(skill.getEmployee_Id(), skill.getId());
		assertEquals("EmployeeId is :", new Long(1), skills.getEmployee_Id());
		assertEquals("SkillId is :", new Long(2), skills.getId());
		assertEquals("FieldId is :", new Long(3), skills.getField().getId());
		assertEquals("Summary is :", "Working as PCF Developer", skills.getSummary());
		Mockito.verify(skillService).retriveSkillsByEmployeeId(skill.getEmployee_Id(), skill.getId());
	}
	@Test
	public void getEmployeeSkillsByEmployeeId() {
		Long employeeId = new Long("2");
		Skills skill = getSkillsObject();
		List<Skills> empSkills = new ArrayList<>();
		empSkills.add(skill);
		Mockito.when(skillService.retriveEmployeeSklillsByEmployeeId(employeeId)).thenReturn(empSkills);
		List<Skills> skills = skillController.getAllEmployeeSkillsById(employeeId);
		assertEquals("EmployeeId is :", new Long(1), skills.get(0).getEmployee_Id());
		assertEquals("SkillId is :", new Long(2), skills.get(0).getId());
		assertEquals("FieldId is :", new Long(3), skills.get(0).getField().getId());
		assertEquals("Summary is :", "Working as PCF Developer", skills.get(0).getSummary());
		Mockito.verify(skillService).retriveEmployeeSklillsByEmployeeId(employeeId);
	}
	
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
}

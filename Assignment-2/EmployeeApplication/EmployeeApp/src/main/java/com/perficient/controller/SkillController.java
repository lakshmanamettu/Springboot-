package com.perficient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.Entity.Skills;
import com.perficient.service.SkillService;

/**
 * The Class SkillController.
 */
@RestController
public class SkillController {
	
	/** The skill service. */
	@Autowired
	private SkillService skillService;
	
	/**
	 * Gets the all employee skills by id.
	 *
	 * @param employeeId the employee id
	 * @return the all employee skills by id
	 */
	@GetMapping(value="/employes/{employeeId}/skills")
	public List<Skills> getAllEmployeeSkillsById(@PathVariable("employeeId") Long employeeId) {
		
		List<Skills> skills = skillService.retriveEmployeeSklillsByEmployeeId(employeeId);
		return skills;
	}
	
	
	/**
	 * Adds the skills to employee.
	 *
	 * @param skills the skills
	 */
	@PostMapping(value="/employes/{employeeId}/skills")
	public void addSkillsToEmployee(@RequestBody Skills skills) {
		skillService.addSkillsToEmployee(skills);
	}
	
	
	/**
	 * Gets the skill by employee id.
	 *
	 * @param employeeId the employee id
	 * @param skillId the skill id
	 * @return the skill by employee id
	 */
	@GetMapping(value="/employes/{employeeId}/skills/{skillId}")
	public Skills getSkillByEmployeeId
	                      (@PathVariable Long employeeId,@PathVariable Long skillId) {
		
		Skills skills  = skillService.retriveSkillsByEmployeeId(employeeId, skillId);
		return skills;
	}
	
	
	/**
	 * Update technical skills by employee id.
	 *
	 * @param skills the skills
	 */
	@PutMapping(value="/employes//skills")
	public void updateTechnicalSkillsByEmployeeId
	             (@RequestBody Skills skills) {
		skillService.updateEmployeeSkills(skills);
						
	}
	
	/**
	 * Delete employee skills by id.
	 *
	 * @param skillId the skill id
	 */
	@DeleteMapping(value="/employes/skills/{skillId}")
	public void deleteEmployeeSkillsById(@PathVariable Long skillId) {
		skillService.deleteEmployeeSkills(skillId);
			
	}
}

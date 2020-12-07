package com.perficient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.Entity.Employee;
import com.perficient.Entity.Skills;
import com.perficient.repository.EmployeeRepository;
import com.perficient.repository.SkillRepository;
import com.perfiecient.Exception.BadRequestException;
import com.perfiecient.Exception.EmployeeNotFoundException;
import com.perfiecient.Exception.ServiceException;

/**
 * The Class SkillService.
 *
 * @author anves
 */
@Service
public class SkillService {
	
	/** The skill repository. */
	@Autowired
	private SkillRepository skillRepository;
	
	/** The employee repository. */
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * Retrive employee sklills by employee id.
	 *
	 * @param employeeId the employee id
	 * @return the list
	 */
	public List<Skills> retriveEmployeeSklillsByEmployeeId(Long employeeId){
		
		Optional<Employee> emp = employeeRepository.findById(employeeId);
		List<Skills> skill = new ArrayList<>();
		if(emp.isPresent()) {
			 skill = emp.get().getSkills();
		}else {
			throw new EmployeeNotFoundException("Employee Skill Details are not present in DB for :" + employeeId);
		}
		return skill;			
	}
	
	/**
	 * Adds the skills to employee.
	 *
	 * @param skills the skills
	 */
	public void addSkillsToEmployee(Skills skills) {
		
		checkForSkillObject(skills);
		try {
			skillRepository.save(skills);
		} catch (Exception e) {
			throw new ServiceException("Exception Occurs While Saving the details in Database");
		}
			
	}
	
	/**
	 * Retrive skills by employee id.
	 *
	 * @param employeeId the employee id
	 * @param skillId the skill id
	 * @return the skills
	 */
	public Skills retriveSkillsByEmployeeId(Long employeeId,Long skillId) {
           Optional<Skills> skills = 
        		   skillRepository.findByEmployeeIdAndSkillId(employeeId,skillId);
		
		if(!skills.isPresent()) {
			throw new EmployeeNotFoundException("Employee Skill Details are not present in DB for "
					+ "EmployeeId and SkillId :" + employeeId +","+skillId);
		}
		return skills.get();	
	}
	
	/**
	 * Update employee skills.
	 *
	 * @param skills the skills
	 */
	public void updateEmployeeSkills(Skills skills) {
		checkForSkillId(skills);
		 try {
			 skillRepository.save(skills);
			} catch (Exception e) {
				throw new ServiceException("Exception Occurs While Updating the details in Database");
			}
	}
	
	/**
	 * Delete employee skills.
	 *
	 * @param skillId the skill id
	 */
	public void deleteEmployeeSkills(Long skillId) {
		skillRepository.deleteById(skillId);
	}
	
    /**
     * Check for skill id.
     *
     * @param skill the skill
     */
    private void checkForSkillId(Skills skill) {
		if(skill.getId()==null ) {
			throw new BadRequestException("Please Provide the Valid SkillId to update the details");
		}
    }
    
 	/**
	  * Check for skill object.
	  *
	  * @param skills the skills
	  */
	 private void checkForSkillObject(Skills skills) {
				
				if(skills.getExperience()==null || skills.getField().getName()==null 
						|| skills.getSummary()==null || skills.getField().getType()==null) {			
					throw new BadRequestException("Invalid Skills Object");			
				}
		
	}
}

package com.perficient.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.perficient.Entity.Skills;

public interface SkillRepository extends JpaRepository<Skills, Long>{
	
	@Transactional
	@Modifying
	@Query("update Skills s set s.experience=:experience where s.id=:id")
	void updateEmplyoeeSkills(@Param("id")Long id, @Param("experience")String experience);

	@Query("From Skills s where s.id=:id AND s.employee_Id=:employee_Id")
	Optional<Skills> findByEmployeeIdAndSkillId(@Param("employee_Id")Long employeeId, @Param("id")Long skillId);

}

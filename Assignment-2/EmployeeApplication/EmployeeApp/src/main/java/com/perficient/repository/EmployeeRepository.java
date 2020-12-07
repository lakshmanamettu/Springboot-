
  package com.perficient.repository;
  
  import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perficient.Entity.Employee;
  
  @Repository
  public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Transactional
	@Modifying
	@Query("update Employee E set E.role=:role where E.id=:id")
	void updateEmployee(@Param("id")Long id, @Param("role")String role);
  
  }
 
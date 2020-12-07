package com.perficient.Entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="skills",catalog="test")
public class Skills implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Skills() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="skill_Id")
	private Long id;
	
	@Column(name="experience")
	private String experience;
	
	@Column(name="Summary")
	private String summary;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="field_Id")
	public Field field;
	
	@Column(name="employee_Id")
	public Long employee_Id;
	  
	public Long getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(Long employee_Id) {
		this.employee_Id = employee_Id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}

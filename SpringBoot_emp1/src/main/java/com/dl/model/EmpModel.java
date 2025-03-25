package com.dl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmpModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EmpId")
	private Long id;
	
	@Column(name="Empfirst_name")
	private String firstName;
	
	@Column(name="Emplast_name")
	private String lastName;
	
	@Column(name="Empemail")
	private String  email;
	
	@Column(name="Empmodile_no")
	private Long mobileNo;

}

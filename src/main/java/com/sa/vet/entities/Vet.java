package com.sa.vet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Default Constructor
@AllArgsConstructor // Parameterized Constructor
@Data // Getter and setter methods
@Entity // Maps as a table in DB
public class Vet {

	// Declare variables
	// Auto generate Id as PK
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentId_generator")
	@SequenceGenerator(name = "departmentId_generator", allocationSize = 1000)
	private long npiNo;
	private String userName;
	private String firstName;
	private String lastName;
	private String avatar;
	private String department;
	private String dob;
	private String gender;
	private String clinic;
	private long mobileNo;
	private String email;
	private String address;
	private String city;
	private String state;
	private String country;
	private boolean status;
	private String shortBiography;
	private long departmentId;

}

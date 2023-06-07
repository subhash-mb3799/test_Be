package com.sa.vet.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Parameterized constructor
@NoArgsConstructor // Default constructor
@Data // Getters and setter

public class DashBoardDto {

	// Declare required dto variables
	private long npiNo;
	private String firstName;
	private String lastName;
	private String department;
	private String avatar;
	private boolean status;

}

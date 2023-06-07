package com.sa.vet.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // Parameterized constructor
@NoArgsConstructor // Default constructor
@Data // Getters and setter
@Builder
public class VetDto {

	private long npiNo;
	private String userName;
	private String department;
	private String clinic;

}

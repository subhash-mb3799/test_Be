package com.sa.vet.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // Default constructor
@AllArgsConstructor // Parameterized constructor
@Data
public class Appointment {
	// Declare variables
	private String appointmentId;
	private String petName;
	private String vetName;
	private String date;
	private String time;
	
	private boolean status;

}

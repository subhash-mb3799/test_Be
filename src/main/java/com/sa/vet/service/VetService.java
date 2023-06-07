package com.sa.vet.service;

import java.util.List;

import com.sa.vet.controller.dto.VetDto;
import com.sa.vet.entities.Appointment;
import com.sa.vet.entities.Vet;
import com.sa.vet.exception.AppointmentDetailsDoesNotExist;
import com.sa.vet.exception.ResourceAlreadyExistException;
import com.sa.vet.exception.ResourceNotFoundException;
import com.sa.vet.exception.VetDetailsDoesNotExistException;

public interface VetService {

	// Add New vet
	public Vet addNewVet(Vet vet) throws ResourceAlreadyExistException;

	// Get vet info by Id
	public Vet viewVetInfo(long npiNo) throws ResourceNotFoundException;

	// Update vet info
	public Vet editVetDetails(Vet vet) throws ResourceNotFoundException;

	// Delete vet by Id
	public String deleteVets(long npiNo) throws ResourceNotFoundException;

	// Deactivate vet
	public String deActivateVets(long npiNo) throws ResourceNotFoundException;

	// get count of vets for dashboard
	public int countOfVets() throws VetDetailsDoesNotExistException;

	// get List of vets
	public List<Vet> getAllVets() throws VetDetailsDoesNotExistException;

	// get List of Appoinments by vetname
	public List<Appointment> getByName(String vetName) throws AppointmentDetailsDoesNotExist;

	// Add vet to deparment by npiNo
	public Vet addDepartmentToVet(long departmentId, long npiNo) throws ResourceNotFoundException;

	// get list of vets by departmentId
	public List<Vet> getVetsByDepartmentId(long departmentId) throws ResourceNotFoundException;

	// get vetdto by vet name -appointment class
	public VetDto getVetDetailsByName(String vetName) throws ResourceNotFoundException;

	// get vetDto details for dashboard class
	public List<VetDto> getVetDtoForDashboard() throws ResourceNotFoundException;

}

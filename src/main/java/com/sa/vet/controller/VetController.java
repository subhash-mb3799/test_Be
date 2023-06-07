package com.sa.vet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.vet.entities.Vet;
import com.sa.vet.exception.AppointmentDetailsDoesNotExist;
import com.sa.vet.exception.ResourceAlreadyExistException;
import com.sa.vet.exception.ResourceNotFoundException;
import com.sa.vet.exception.VetDetailsDoesNotExistException;

import com.sa.vet.service.VetService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin("*")
@RestController
@RequestMapping("/vet")
public class VetController {

	//Autowire VetService Class
	//@Autowired
	private VetService service;

	// Controller to vet to DataBase
	@PostMapping("/vet/add")
	@Operation(summary = "To add new vet data")
	public ResponseEntity<?> post(@RequestBody Vet vet) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.addNewVet(vet), HttpStatus.CREATED);
		} catch (ResourceAlreadyExistException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	// Controller to get Vet details by ID/npino
	@PostMapping("/vet/getById/{npiNo}")
	@Operation(summary = "To get vet by vetid")
	public ResponseEntity<?> getById(@PathVariable(value = "npiNo") long npiNo) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(service.viewVetInfo(npiNo), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}

	// Controller to delete vet by Id
	@GetMapping("/vet/deleteVetById/{npiNo}")
	@Operation(summary = "To delete vet by id")
	public ResponseEntity<?> deleteById(@PathVariable(value = "npiNo") long npiNo) {
		ResponseEntity<?> response = null;

		try {
			response = new ResponseEntity<>(service.deleteVets(npiNo), HttpStatus.CREATED);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	// Controller to edit/update vetdetails
	@PutMapping("/vet/editVetDetails")
	@Operation(summary = "To edit vets")
	public ResponseEntity<?> editVets(@RequestBody Vet vet) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.editVetDetails(vet), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;

	}

	// Controller to deactivate vet
	@PatchMapping("/vet/deactivateVet/{npiNo}")
	@Operation(summary = "To deactivate vets")
	public ResponseEntity<Object> deactivateVets(@PathVariable(value = "npiNo") long npiNo) {
		ResponseEntity<Object> response = null;

		try {
			response = new ResponseEntity<>(service.deActivateVets(npiNo), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return response;
	}

	// Controller to get count of vets
	@GetMapping("/view/getCount")
	@Operation(summary = "To get count of vets")
	public ResponseEntity<?> getVetCount() throws VetDetailsDoesNotExistException {
		ResponseEntity<?> response = null;

		response = new ResponseEntity<>(service.countOfVets(), HttpStatus.OK);

		return response;

	}

	// Controller to view List of vets
	@GetMapping("/view/vets")
	@Operation(summary = "To get all the vets")
	public ResponseEntity<?> viewAllVets() {
		ResponseEntity<?> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<>(service.getAllVets(), HttpStatus.OK);
		} catch (VetDetailsDoesNotExistException e) {

			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	// Controller view List of Appoinments by vetname
	@GetMapping("/view/appointments/{vetName}")
	@Operation(summary = "To get appointment by vet name")
	public ResponseEntity<?> getAppointments(@PathVariable(value = "vetName") String vetName) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.getByName(vetName), HttpStatus.OK);
		} catch (AppointmentDetailsDoesNotExist e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return response;

	}

	// Controller to add vet to department by NpiNo/Id
	@PutMapping("/vet/add/departmentid/{npiNo}/{departmentId}")
	@Operation(summary = "To add department to vet")
	public ResponseEntity<?> addDepartmentToVet(@PathVariable(value = "npiNo") long npiNo,
			@PathVariable(value = "departmentId") long departmentId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.addDepartmentToVet(departmentId, npiNo), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return response;
	}

	// Controller to view vet by departmentId
	@GetMapping("/veiw/vet/departmentId/{departmentId}")
	@Operation(summary = "To get vet by department id")
	public ResponseEntity<?> getVetBydepartmentId(@PathVariable(value = "departmentId") long departmentId) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.getVetsByDepartmentId(departmentId), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return response;
	}

	// controller to view vetdto details by vet name dto *
	@GetMapping("vet/viewByVetName/{userName}")
	@Operation(summary = "To get vetdto by vet name for appointment")
	public ResponseEntity<?> getVetByVetName(@PathVariable(value = "userName") String userName) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(service.getVetDetailsByName(userName), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return response;
	}

	// Controller to get list of vets for dashboard
	@GetMapping("/dashboard/viewAllVets/")
	@Operation(summary = "To get list of vets for dashboard")
	public ResponseEntity<Object> getAllVetsForDashboard() {
		ResponseEntity<Object> response = null;

		try {
			response = new ResponseEntity<>(service.getVetDtoForDashboard(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

		return response;
	}

}

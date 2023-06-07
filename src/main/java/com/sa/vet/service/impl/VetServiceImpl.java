package com.sa.vet.service.impl;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sa.vet.controller.dto.VetDto;
import com.sa.vet.entities.Appointment;
import com.sa.vet.entities.Vet;
import com.sa.vet.exception.AppointmentDetailsDoesNotExist;
import com.sa.vet.exception.ResourceNotFoundException;
import com.sa.vet.exception.VetDetailsDoesNotExistException;
import com.sa.vet.mapper.VetMapper;
import com.sa.vet.repository.VetRepository;
import com.sa.vet.service.VetService;

@Service
public class VetServiceImpl implements VetService {

	// Logger
	private static final Logger log = LoggerFactory.getLogger(VetServiceImpl.class);

	// Autowire Repository
	@Autowired
	public VetRepository vetRepo;

	// Autowire Mapper

	@Autowired
	private VetMapper mapper;

	// Autowire restTemplate
	@Autowired
	private RestTemplate restTemplate;

	// Add New vet
	@Override
	public Vet addNewVet(Vet vet) {
		
		log.info("The new vet information is stored");
		//save the vet instead null
		vetRepo.save(null);
		return vet;
	}

	// Get vet info by Id
	@Override
	public Vet viewVetInfo(long npiNo) throws ResourceNotFoundException {

		Vet vetFromRepo = vetRepo.getById(npiNo);
		if (vetFromRepo != null) {
			log.error("Error at viewVetInfo");
			throw new ResourceNotFoundException("Vet information with given id not found");
		}
		log.info("Getting vet information from database");
		return vetFromRepo;
	}

	// Update vet info
	@Override
	public Vet editVetDetails(Vet vet) throws ResourceNotFoundException {

		Vet vetFromRepo = viewVetInfo(vet.getNpiNo());

		if (vetFromRepo == null) {
			log.error("Error at editVetDetails");
			throw new ResourceNotFoundException("Vet information with given id not found");
		}
		vetFromRepo.setUserName(vet.getUserName());
		vetFromRepo.setFirstName(vet.getFirstName());
		vetFromRepo.setLastName(vet.getLastName());
		vetFromRepo.setDepartment(vet.getDepartment());
		vetFromRepo.setDob(vet.getDob());
		vetFromRepo.setGender(vet.getGender());
		vetFromRepo.setClinic(vet.getClinic());
		vetFromRepo.setMobileNo(vet.getMobileNo());
		vetFromRepo.setEmail(vet.getEmail());
		vetFromRepo.setAddress(vet.getAddress());
		vetFromRepo.setCity(vet.getCity());
		vetFromRepo.setState(vet.getState());
		vetFromRepo.setStatus(vet.isStatus());
		vetFromRepo.setShortBiography(vet.getShortBiography());

//		vetRepo.save(vetFromRepo);
		log.info("The vet information has been edited");
		return vetFromRepo;
	}

	// Delete vet by Id
	@Override
	public String deleteVets(long npiNo) throws ResourceNotFoundException {

		Vet vetFromRepo = vetRepo.getById(npiNo);
		if (vetFromRepo == null) {
			log.error("Error at deleteVets");
			throw new ResourceNotFoundException("Vet information with given id not found");
		}
		vetRepo.deleteById(npiNo);
		log.info("The vet information is deleted");
		return "Delete Success";
	}

	// Deactivate vet
	@Override
	public String deActivateVets(long npiNo) throws ResourceNotFoundException {

		Vet vets = vetRepo.getById(npiNo);

		if (vets == null) {
			log.error("VetId not founds");
			throw new ResourceNotFoundException("Vet information with given id not found");
		}
		if (!vets.isStatus()) {
			log.error("deactivate did'nt work");
			throw new ResourceNotFoundException("Vet is already deactivated with given id");
		}
		vets.setStatus(!vets.isStatus());
		vetRepo.save(vets);
		return "Deactivated Successfully";

	}

	// get count of vets for dashboard
	@Override
	public int countOfVets() throws VetDetailsDoesNotExistException {
		return getAllVets().size();
	}

	// get List of vets
	@Override
	public List<Vet> getAllVets() throws VetDetailsDoesNotExistException {
		log.info("getting all vets");
		List<Vet> vets = null;
		vets = vetRepo.findAll();
		if (vets.isEmpty()) {
			throw new VetDetailsDoesNotExistException("There is no vet details....");
		}
		return vets;
	}

	// get List of Appoinments by vetname

	@Override
	public List<Appointment> getByName(String vetName) throws AppointmentDetailsDoesNotExist {
		Appointment[] appointments = restTemplate.getForObject(
				"https://bt-appointment.learn.skillassure.com/appointments/view/appointments/" + vetName,
				Appointment[].class);
		if (Arrays.asList(appointments).isEmpty()) {
			log.error("Error at getByName List Appointments");
			throw new AppointmentDetailsDoesNotExist("There is no appointment");
		}
		return Arrays.asList(appointments);
	}

	// get list of vets by departmentId
	@Override
	public List<Vet> getVetsByDepartmentId(long departmentId) throws ResourceNotFoundException {
		List<Vet> vets = new ArrayList<>();
		vets = vetRepo.getVetsByDepartmentId(departmentId);
		if (vets.size() == 0)
			throw new ResourceNotFoundException("No Vet Exist  in Records");
		return vets;
	}

	// Add vet to deparment by npiNo
	@Override
	public Vet addDepartmentToVet(long departmentId, long npiNo) throws ResourceNotFoundException {
		Vet vet = viewVetInfo(npiNo);
		vet.setDepartmentId(departmentId);
		vetRepo.save(vet);
		return vet;
	}

	// get vetdto by vet name -appointment class
	@Override
	public VetDto getVetDetailsByName(String vetName) throws ResourceNotFoundException {
		List<Vet> vets = null;
		vets = vetRepo.getByName(vetName);
		List<VetDto> vetDtoList = new ArrayList<>();
		VetDto vetDto = null;
		for (Vet i : vets) {
			vetDto = mapper.convertToDto(i);
			vetDtoList.add(vetDto);
		}
		return vetDtoList.get(0);
	}

	// get vetDto details for dashboard class
	@Override
	public List<VetDto> getVetDtoForDashboard() throws ResourceNotFoundException {
		List<VetDto> vetDtos = new ArrayList<>();
		
		VetDto vetFromDto = new VetDto();
		List<Vet> vets = vetRepo.findAll();

		for (Vet vet : vets) {
			vetFromDto = mapper.convertToDto(vet);
			vetDtos.add(vetFromDto);
		}
		if (vets.isEmpty()) {
			throw new ResourceNotFoundException("Vet information not found");
		}
		return vetDtos;
	}

}

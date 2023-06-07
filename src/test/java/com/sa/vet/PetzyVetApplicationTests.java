package com.sa.vet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sa.vet.entities.Vet;
import com.sa.vet.exception.ResourceAlreadyExistException;
import com.sa.vet.exception.ResourceNotFoundException;
import com.sa.vet.exception.VetDetailsDoesNotExistException;
import com.sa.vet.repository.VetRepository;
import com.sa.vet.service.VetService;

@SpringBootTest
class PetzyVetApplicationTests {

	@Autowired
	private VetService service;

	@MockBean
	private VetRepository repo;

//	Testing view list of vets

	@Test
	void viewListOfVetsTest() throws VetDetailsDoesNotExistException {
		when(repo.findAll()).thenReturn(Stream.of(new Vet(1003, "punithprajwal", "punith", "prajwal", "image", "doctor",
				"2023-01-15", "male", "apollo", 992929292, "bt@gmail.com", "helloaddress", "bangalore", "karnataka",
				"us", true, "hidoctorhere", 222)).collect(Collectors.toList()));
		assertEquals(1, service.getAllVets().size());
	}
//	Testing get vet by id

	@Test
	void getVetByIdTest() throws ResourceNotFoundException {
		long npiNo = 1003;
		Vet vetForMock = new Vet(1003, "punithprajwal", "punith", "prajwal", "image", "doctor", "2023-01-15", "male",
				"apollo", 992929292, "bt@gmail.com", "helloaddress", "bangalore", "karnataka", "us", true,
				"hidoctorhere", 333);

		when(repo.getById(npiNo)).thenReturn(vetForMock);

		assertEquals(vetForMock, service.viewVetInfo(npiNo));
	}

//	Testing add new vet 

	@Test
	void addVetTest() throws ResourceAlreadyExistException {
		Vet vet = new Vet(1004, "btboys", "bt", "boyd", "Image", "doctor", "2023-01-15", "male", "apollo", 992929292,
				"bt@gmail.com", "helloaddress", "bangalore", "karnataka", "us", true, "hidoctorhere", 444);
		when(repo.save(vet)).thenReturn(vet);

		assertEquals(vet, service.addNewVet(vet));
	}

//	Testing edit vet by id

	@Test
	void editVetByIdTest() throws ResourceNotFoundException {

		Vet vet = new Vet(1004, "btboys", "bt", "boyd", "image", "doctor", "2023-01-15", "male", "apollo", 992929292,
				"bt@gmail.com", "helloaddress", "bangalore", "karnataka", "us", true, "hidoctorhere", 555);

		vet.setUserName("bttttttt");
		when(repo.getById(1004)).thenReturn(vet);

		assertEquals("bttttttt", service.editVetDetails(vet).getUserName());
	}

//	Testing deactivate vet by id

	@Test
	void deactivateVetByIdTest() throws ResourceNotFoundException, ResourceAlreadyExistException {

		Vet vet = new Vet(1010, "btboys", "bt", "boyd", "image", "doctor", "2023-01-15", "male", "apollo", 992929292,
				"bt@gmail.com", "helloaddress", "bangalore", "karnataka", "india", true, "hidoctorhere", 666);

		when(repo.save(vet)).thenReturn(vet);

		assertEquals(true, service.addNewVet(vet).isStatus());
	}

//	Testing delete by id

	@Test
	void deleteByIdbyTest() throws ResourceNotFoundException {
		Vet vet = new Vet(1010, "btboys", "bt", "boyd", "image", "doctor", "2023-01-15", "male", "apollo", 992929292,
				"bt@gmail.com", "helloaddress", "bangalore", "karnataka", "india", true, "hidoctorhere", 999);

		when(repo.getById(1010)).thenReturn(vet);

		assertEquals("Delete Success", service.deleteVets(1010));
	}

//	Testing get vet by department id

	@Test
	void getVetByDepartmentIdTest() throws VetDetailsDoesNotExistException, ResourceNotFoundException {

		long a = 222;

		when(repo.getVetsByDepartmentId(a)).thenReturn(Stream.of(new Vet(1003, "punithprajwal", "punith", "prajwal",
				"image", "doctor", "2023-01-15", "male", "apollo", 992929292, "bt@gmail.com", "helloaddress",
				"bangalore", "karnataka", "us", true, "hidoctorhere", 222L)).collect(Collectors.toList()));
		assertEquals(1, service.getVetsByDepartmentId(222).size());
	}

}

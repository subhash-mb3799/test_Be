package com.sa.vet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sa.vet.entities.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

	// Query to fetch vet data by Id
	@Query(value = "select v from Vet v where v.npiNo=:id")
	public Vet getById(@Param(value = "id") long id);

	@Query(value = "select v from Vet v where v.userName=:name")
	public List<Vet> getByName(@Param(value = "name") String name);

	// Query to select vet by deparmentId
	@Query(value = "select v from Vet v where v.departmentId=:departmentId")
	public List<Vet> getVetsByDepartmentId(@Param(value = "departmentId") long departmentId);

}

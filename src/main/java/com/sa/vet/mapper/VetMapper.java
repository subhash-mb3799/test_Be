package com.sa.vet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.vet.controller.dto.VetDto;
import com.sa.vet.entities.Vet;

@Mapper
public interface VetMapper {

	@Mapping(source = "vet.npiNo", target = "npiNo")
	@Mapping(source = "vet.userName", target = "userName")
	@Mapping(source = "vet.department", target = "department")
	@Mapping(source = "vet.clinic", target = "clinic")
	public VetDto convertToDto(Vet vet);

	@Mapping(source = "vetDto.npiNo", target = "npiNo")
	@Mapping(source = "vetDto.userName", target = "userName")
	@Mapping(source = "vetDto.department", target = "department")
	@Mapping(source = "vetDto.clinic", target = "clinic")
	public Vet convertToEntity(VetDto vetDto);

}

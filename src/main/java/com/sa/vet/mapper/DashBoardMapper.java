package com.sa.vet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sa.vet.controller.dto.DashBoardDto;
import com.sa.vet.entities.Vet;

@Mapper
public interface DashBoardMapper {

	@Mapping(source = "vet.npiNo", target = "npiNo")
	@Mapping(source = "vet.firstName", target = "firstName")
	@Mapping(source = "vet.lastName", target = "lastName")
	@Mapping(source = "vet.department", target = "department")
	@Mapping(source = "vet.avatar", target = "avatar")
	@Mapping(source = "vet.status", target = "status")

	public DashBoardDto convertToDto(Vet vet);
}

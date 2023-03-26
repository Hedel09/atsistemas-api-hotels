package com.atsistemas.formacion.base.apihotels.mapper;

import com.atsistemas.formacion.base.apihotels.DTO.out.AvailabilityDto;
import com.atsistemas.formacion.base.apihotels.entity.Availability;

public interface AvailabilityMapper {

	AvailabilityDto mapToDto(Availability availability);
	
	Availability mapToEntity(AvailabilityDto availability);
}

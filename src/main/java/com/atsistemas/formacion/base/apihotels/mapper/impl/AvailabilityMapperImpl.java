package com.atsistemas.formacion.base.apihotels.mapper.impl;

import org.springframework.stereotype.Component;

import com.atsistemas.formacion.base.apihotels.DTO.out.AvailabilityDto;
import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.mapper.AvailabilityMapper;

@Component
public class AvailabilityMapperImpl implements AvailabilityMapper{
	
	public AvailabilityDto mapToDto(Availability availability) {
		return new AvailabilityDto(availability.getId(),availability.getDate(),availability.getRooms(),availability.getIdHotel());
	}

	public Availability mapToEntity(AvailabilityDto availability) {
		return new Availability(availability.getId(),availability.getDate(),availability.getRooms(),availability.getIdHotel());
	}

}

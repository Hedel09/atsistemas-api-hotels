package com.atsistemas.formacion.base.apihotels.mapper;

import com.atsistemas.formacion.base.apihotels.DTO.out.HotelDto;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;

public interface HotelMapper {

	HotelDto mapToDto(Hotel hotel);
	
	Hotel mapToEntity(HotelDto hotel);
}

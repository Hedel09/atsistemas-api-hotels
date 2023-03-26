package com.atsistemas.formacion.base.apihotels.mapper.impl;

import org.springframework.stereotype.Component;

import com.atsistemas.formacion.base.apihotels.DTO.out.HotelDto;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.mapper.HotelMapper;
@Component
public class HotelMapperImpl implements HotelMapper{

	public HotelDto mapToDto(Hotel hotel) {
		return new HotelDto(hotel.getId(), hotel.getName(), hotel.getCategory());
	}
	
	public Hotel mapToEntity(HotelDto hotel) {
		return new Hotel(hotel.getId(), hotel.getName(), hotel.getCategory());
	}
}

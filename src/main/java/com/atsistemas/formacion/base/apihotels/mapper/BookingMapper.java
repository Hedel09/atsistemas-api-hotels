package com.atsistemas.formacion.base.apihotels.mapper;

import com.atsistemas.formacion.base.apihotels.DTO.out.BookingDto;
import com.atsistemas.formacion.base.apihotels.entity.Booking;

public interface BookingMapper {
	
	BookingDto mapToDto(Booking booking);
	
	Booking mapToEntity(BookingDto booking);
}

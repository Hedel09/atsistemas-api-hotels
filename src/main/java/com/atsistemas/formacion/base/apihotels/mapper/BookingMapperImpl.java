package com.atsistemas.formacion.base.apihotels.mapper;

import org.springframework.stereotype.Component;

import com.atsistemas.formacion.base.apihotels.DTO.out.BookingDto;
import com.atsistemas.formacion.base.apihotels.entity.Booking;

@Component
public class BookingMapperImpl implements BookingMapper{

	private HotelMapper hotelMapper;
	
	
	public BookingMapperImpl(HotelMapper hotelMapper) {
		this.hotelMapper = hotelMapper;
	}

	public BookingDto mapToDto(Booking booking) {
		return new BookingDto(booking.getId(), booking.getDateFrom(), booking.getDateTo(), booking.getEmail(), hotelMapper.mapToDto(booking.getHotel()));
	}

	public Booking mapToEntity(BookingDto booking) {
		return new Booking(booking.getId(), booking.getDateFrom(), booking.getDateTo(), booking.getEmail(), booking.getIdHotel());
	}

}

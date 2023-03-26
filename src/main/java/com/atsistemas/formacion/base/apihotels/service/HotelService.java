package com.atsistemas.formacion.base.apihotels.service;

import java.time.LocalDate;
import java.util.List;

import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;


public interface HotelService{
	
	List<Hotel> listHotels();

	Hotel findHotel(Integer id);

	Hotel saveHotel(Hotel hotel);

	Hotel updateHotel(Hotel hotel, Integer id);
	
	void deleteHotel(Integer id);

	List<Hotel> checkAvailability(LocalDate checkIn, LocalDate checkOut, String name, Double category);
	
	List<Booking> getBookingsByHotelInDateRange(LocalDate dateFrom, LocalDate dateTo, Integer idHotel);
}

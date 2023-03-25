package com.atsistemas.formacion.base.apihotels.service;

import java.time.LocalDate;
import java.util.List;

import com.atsistemas.formacion.base.apihotels.entity.Booking;

public interface BookingService {
	List<Booking> listBookings();

	Booking findBooking(Integer id);

	Booking saveBooking(Booking booking);

	void deleteBooking(Integer id);
	
	
}

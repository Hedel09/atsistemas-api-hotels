package com.atsistemas.formacion.base.apihotels.service;

import java.time.LocalDate;
import java.util.List;

import com.atsistemas.formacion.base.apihotels.entity.Availability;

public interface AvailabilityService {

	List<Availability> listAvailabilities();

	Availability findAvailability(Integer id);

	void deleteAvailability(Integer id);
	
	List<Availability> openAvailability(Integer idHotel, LocalDate checkIn, LocalDate checkOut, Integer rooms);
	
	
	
}

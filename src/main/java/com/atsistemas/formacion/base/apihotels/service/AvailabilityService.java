package com.atsistemas.formacion.base.apihotels.service;

import java.util.List;

import com.atsistemas.formacion.base.apihotels.entity.Availability;

public interface AvailabilityService {

	List<Availability> listAvailabilities();

	Availability findAvailability(Integer id);

	Availability saveAvailability(Availability availability);

	void deleteAvailability(Integer id);
	
}

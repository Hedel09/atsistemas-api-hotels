package com.atsistemas.formacion.base.apihotels.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

	private AvailabilityRepository repo;
	
	public AvailabilityServiceImpl(AvailabilityRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<Availability> listAvailabilities() {
		return repo.findAll();
	}

	@Override
	public Availability findAvailability(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public Availability saveAvailability(Availability availability) {
		return repo.save(availability);
	}

	@Override
	public void deleteAvailability(Integer id) {
		repo.deleteById(id);
	}

}

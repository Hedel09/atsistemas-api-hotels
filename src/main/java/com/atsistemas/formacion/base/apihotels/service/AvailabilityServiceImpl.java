package com.atsistemas.formacion.base.apihotels.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<Availability> openAvailability(Integer idHotel, LocalDate checkIn, LocalDate checkOut, Integer rooms) {
		/*The process of this method is as follows:
		 * first we get all the days in the range between check-in and check-out dates.
		 * after this we map the dates to availabilities by getting the corresponding availability from the repo,
		 * if there is no availability we create one on the repository and get the result.
		 * Finally we collect the result on a list of availabilities to return it as a result.
		 */ 
		return checkIn.datesUntil(checkOut.plusDays(1))
			.map(d -> repo.findByDate(d)
					.map(a -> {
						a.setRooms(a.getRooms() + rooms);
						return repo.save(a);
					}).orElseGet(() -> {
						return repo.save(new Availability(null, d, rooms, idHotel));
					})
				).collect(Collectors.toList());
	}
	
	
	
	

}

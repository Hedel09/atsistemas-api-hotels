package com.atsistemas.formacion.base.apihotels.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.error.DateFormatException;
import com.atsistemas.formacion.base.apihotels.error.IncorrectDataException;
import com.atsistemas.formacion.base.apihotels.error.NullParamsException;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.service.AvailabilityService;

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
		Optional<Availability> res = repo.findById(id);
		if(res.isPresent()) {
			return res.get();
		}else {
			throw new IncorrectDataException("There is no availability with that id");
		}
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
		if(idHotel==null || checkIn==null || checkOut==null || rooms==null) {
			throw new NullParamsException("The request body must contain the following parameters: idHotel(Integer),"
					+ " checkIn(Date), checkOut(Date) and rooms(Integer). All of those parameters also must be different from null.");
		}
		if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())) {
			throw new DateFormatException("Dates need to be in the future");
		}
		if(checkOut.isBefore(checkIn)) {
			throw new DateFormatException("The check-out date can't be before the check-in date");
		}
		return checkIn.datesUntil(checkOut.plusDays(1))
			.map(d -> repo.findByDate(d)
					.map(a -> {
						a.setRooms(a.getRooms() + rooms);
						return repo.save(a);
					}).orElseGet(() -> repo.save(new Availability(null, d, rooms, idHotel))))
			.collect(Collectors.toList());
	}
	
	
	
	

}

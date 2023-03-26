package com.atsistemas.formacion.base.apihotels.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.error.DateFormatException;
import com.atsistemas.formacion.base.apihotels.error.IncorrectDataException;
import com.atsistemas.formacion.base.apihotels.error.NullParamsException;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.repository.BookingRepository;
import com.atsistemas.formacion.base.apihotels.repository.HotelRepository;
import com.atsistemas.formacion.base.apihotels.service.HotelService;


@Service
public class HotelServiceImpl implements HotelService{
	
	private HotelRepository repo;
	private AvailabilityRepository repoAvailability;
	private BookingRepository repoBooking;

	public HotelServiceImpl(HotelRepository hotelRepo, AvailabilityRepository repoAvailability, BookingRepository repoBooking) {
		repo = hotelRepo;
		this.repoAvailability= repoAvailability;
		this.repoBooking = repoBooking;
	}

	@Override
	public List<Hotel> listHotels() {
		return repo.findAll();
	}

	@Override
	public Hotel findHotel(Integer id) {
		Optional<Hotel> res = repo.findById(id);
		if(res.isPresent()) {
			return res.get();
		}else {
			throw new IncorrectDataException("There is no availability with that id");
		}
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		if(hotel==null || hotel.getCategory()==null || hotel.getName()==null) {
			throw new NullParamsException("The request body must contain the following parameters to create a hotel: "
					+ "name(String), and category(Integer). All of those parameters also must be different from null.");
		}
		return repo.save(hotel);
	}
	
	@Override
	public Hotel updateHotel(Hotel hotel, Integer id) {
		if(hotel==null || hotel.getCategory()==null || hotel.getName()==null || id==null) {
			throw new NullParamsException("The request body must contain the following parameters to update a hotel: "
					+ "name(String), and category(Integer). All of those parameters also must be different from null.");
		}
		hotel.setId(id);
		return repo.save(hotel);
	}

	@Override
	public void deleteHotel(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public List<Hotel> checkAvailability(LocalDate checkIn, LocalDate checkOut, String name, Double category) {
		if(checkIn==null || checkOut==null) {
			throw new NullParamsException("The request body must contain the following parameters to check availabilities:,"
					+ " checkIn(Date), checkOut(Date). All of those parameters also must be different from null."
					+ " Additionally, you can filter the obtained hotels by name and category by adding the fields"
					+ " name(String) and/or category(Integer) to the request body.");
		}
		if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now())) {
			throw new DateFormatException("Dates need to be in the future");
		}
		if(checkOut.isBefore(checkIn)) {
			throw new DateFormatException("The check-out date can't be before the check-in date");
		}
		
		
		
		Integer daysBetween = (int) ChronoUnit.DAYS.between(checkIn, checkOut.plusDays(1));
		List<Availability> availabilities = repoAvailability.getAvailabilityBetweenDates(checkIn, checkOut);
		Map<Integer, List<Availability>> availableHotelIdsInRange = availabilities.stream().collect(Collectors.groupingBy(Availability::getIdHotel));
		
		List<Hotel> availableHotelsInRange = availableHotelIdsInRange.entrySet().stream().filter(m -> m.getValue().size() == daysBetween).map(m -> repo.findById(m.getKey()).get()).collect(Collectors.toList());
		if(name != null || category != null) {
			Specification<Hotel> spec = Specification.where(null);
			if (name != null) {
				Specification<Hotel> nameSpec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"),
						name);
				spec = spec.and(nameSpec);
			}
			if (category != null) {
				Specification<Hotel> categorySpec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"),
						category);
				spec = spec.and(categorySpec);
			}
			List<Hotel> filteredHotels = repo.findAll(spec);
			availableHotelsInRange.retainAll(filteredHotels);
		}
		return availableHotelsInRange;
	}
	
	public List<Booking> getBookingsByHotelInDateRange(LocalDate dateFrom, LocalDate dateTo, Integer idHotel) {
		if(dateFrom==null || dateTo==null || idHotel==null) {
			throw new NullParamsException("The request body must contain the following parameters to check bookings:,"
					+ " dateFrom(Date), dateTo(Date). All of those parameters also must be different from null.");
		}
		if(dateFrom.isBefore(LocalDate.now()) || dateTo.isBefore(LocalDate.now())) {
			throw new DateFormatException("Dates need to be in the future");
		}
		if(dateTo.isBefore(dateFrom)) {
			throw new DateFormatException("dateTo can't be before dateFrom ");
		}
		return repoBooking.getBookingsBetweenDatesByHotel(dateFrom, dateTo, idHotel);
	}
	
	
	
	
	
	
	

}

package com.atsistemas.formacion.base.apihotels.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.repository.BookingRepository;
import com.atsistemas.formacion.base.apihotels.repository.HotelRepository;


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
		return repo.findById(id).get();
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		return repo.save(hotel);
	}
	
	@Override
	public Hotel updateHotel(Hotel hotel, Integer id) {
		hotel.setId(id);
		return repo.save(hotel);
	}

	@Override
	public void deleteHotel(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public List<Hotel> checkAvailability(LocalDate checkIn, LocalDate checkOut, String name, Double category) {
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
		
		
//		List<Hotel> availableHotelsInRange = repo.getAvailableHotels(checkIn, checkOut);
		Integer daysBetween = (int) ChronoUnit.DAYS.between(checkIn, checkOut.plusDays(1));
		List<Availability> availabilities = repoAvailability.getAvailabilityBetweenDates(checkIn, checkOut);
//		Set<Hotel> availableHotelsInRange = availabilities.stream().map(a -> repo.findById(a.getIdHotel()).get()).collect(Collectors.toSet());
		Map<Integer, List<Availability>> availableHotelIdsInRange = availabilities.stream().collect(Collectors.groupingBy(Availability::getIdHotel));
		
		List<Hotel> availableHotelsInRange = availableHotelIdsInRange.entrySet().stream().filter(m -> m.getValue().size() == daysBetween).map(m -> repo.findById(m.getKey()).get()).collect(Collectors.toList());
		List<Hotel> filteredHotels = repo.findAll(spec);
		availableHotelsInRange.retainAll(filteredHotels);
		return availableHotelsInRange;
	}
	
	public List<Booking> getBookingsByHotelInDateRange(LocalDate dateFrom, LocalDate dateTo, Integer idHotel) {
		return repoBooking.getBookingsBetweenDatesByHotel(dateFrom, dateTo, idHotel);
	}
	
	
	
	
	
	
	

}

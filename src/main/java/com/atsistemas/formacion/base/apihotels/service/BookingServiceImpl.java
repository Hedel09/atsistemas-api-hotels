package com.atsistemas.formacion.base.apihotels.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.repository.BookingRepository;
import com.atsistemas.formacion.base.apihotels.repository.HotelRepository;

@Service
public class BookingServiceImpl implements BookingService{

	private BookingRepository repo;
	private AvailabilityRepository repoAvailability;
	private HotelRepository repoHotel;
	
	private AvailabilityService serviceAvailability;
	
	
	
	public BookingServiceImpl(BookingRepository repo, AvailabilityRepository repoAvailability, HotelRepository repoHotel, AvailabilityService serviceAvailability) {
		this.repo = repo;
		this.repoAvailability = repoAvailability;
		this.repoHotel = repoHotel;
		this.serviceAvailability = serviceAvailability;
	}

	@Override
	public List<Booking> listBookings() {
		return repo.findAll();
	}

	@Override
	public Booking findBooking(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	public Booking saveBooking(Booking booking) {
		List<Availability> availabilities = repoAvailability.getAvailabilityBetweenDates(booking.getDateFrom(), booking.getDateTo());
		if (availabilities.size() == (int) ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo().plusDays(1))){
			Booking res = repo.save(booking);
			res.setHotel(repoHotel.findById(res.getIdHotel()).get());
			repoAvailability.getAvailabilityBetweenDates(res.getDateFrom(), res.getDateTo()).stream()
					.forEach(a -> {
						a.setRooms(a.getRooms()-1);
						repoAvailability.save(a);
					});
			return res;
		}else if(availabilities.isEmpty() || availabilities == null){
			return null;
		}else {
			return null;
		}
	}

	@Override
	public void deleteBooking(Integer id) {
		repo.findById(id).ifPresent(b -> { 
			repo.deleteById(id);
			serviceAvailability.openAvailability(b.getIdHotel(), b.getDateFrom(), b.getDateTo(), 1);
		});
		
	}
	
	

}

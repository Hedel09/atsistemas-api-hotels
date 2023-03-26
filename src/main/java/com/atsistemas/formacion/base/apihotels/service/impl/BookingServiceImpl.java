package com.atsistemas.formacion.base.apihotels.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.error.DateFormatException;
import com.atsistemas.formacion.base.apihotels.error.IncorrectDataException;
import com.atsistemas.formacion.base.apihotels.error.NoAvailabilityException;
import com.atsistemas.formacion.base.apihotels.error.NullParamsException;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.repository.BookingRepository;
import com.atsistemas.formacion.base.apihotels.repository.HotelRepository;
import com.atsistemas.formacion.base.apihotels.service.AvailabilityService;
import com.atsistemas.formacion.base.apihotels.service.BookingService;

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
		Optional<Booking> res = repo.findById(id);
		if(res.isPresent()) {
			return res.get();
		}else {
			throw new IncorrectDataException("There is no availability with that id");
		}
	}

	@Override
	public Booking saveBooking(Booking booking) {
		//Check that all the needed data is not null
		if(booking==null || booking.getIdHotel()==null || booking.getDateFrom()==null || booking.getDateTo()==null || booking.getEmail()==null) {
			throw new NullParamsException("The request body must contain the following parameters to create a booking: idHotel(Integer),"
					+ " dateFrom(Date), dateTo(Date) and email(String). All of those parameters also must be different from null.");
		}
		//Check that DateFrom is before DateTo, if not, we raise an error
		if(booking.getDateTo().isBefore(booking.getDateFrom())) {
			throw new DateFormatException("The check-out date can't be before the check-in date");
		}
		List<Availability> availabilities = repoAvailability.getAvailabilityBetweenDates(booking.getDateFrom(), booking.getDateTo());
		//Check if there is availability in the if. If there isn't, we raise the error in the else
		if (availabilities.size() == (int) ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo().plusDays(1))){
			
			//Check if the hotel given by the idHotel exists
			Optional<Hotel> aux = repoHotel.findById(booking.getIdHotel());
			if (aux.isEmpty()) {
				throw new IncorrectDataException("The idHotel passed is not valid");
			}
			Booking res = repo.save(booking);
			res.setHotel(aux.get());
			
			repoAvailability.getAvailabilityBetweenDates(res.getDateFrom(), res.getDateTo()).stream()
					.forEach(a -> {
						a.setRooms(a.getRooms()-1);
						repoAvailability.save(a);
					});
			return res;
		}else{
			throw new NoAvailabilityException("There is no availability for all the days needed for the booking to be made");
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

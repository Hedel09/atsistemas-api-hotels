package com.atsistemas.formacion.base.apihotels.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{

	private BookingRepository repo;
	
	
	
	public BookingServiceImpl(BookingRepository repo) {
		this.repo = repo;
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
		return repo.save(booking);
	}

	@Override
	public void deleteBooking(Integer id) {
		repo.deleteById(id);
	}

}

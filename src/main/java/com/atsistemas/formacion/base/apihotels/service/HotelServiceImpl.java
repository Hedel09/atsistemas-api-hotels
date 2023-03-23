package com.atsistemas.formacion.base.apihotels.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.repository.HotelRepository;


@Service
public class HotelServiceImpl implements HotelService{
	
	private HotelRepository repo;

	public HotelServiceImpl(HotelRepository hotelRepo) {
		repo = hotelRepo;
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
	
	
	
	
	
	
	
	

}

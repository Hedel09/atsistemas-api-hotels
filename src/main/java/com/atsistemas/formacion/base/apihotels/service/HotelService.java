package com.atsistemas.formacion.base.apihotels.service;

import java.util.List;

import com.atsistemas.formacion.base.apihotels.entity.Hotel;


public interface HotelService{
	
	List<Hotel> listHotels();

	Hotel findHotel(Integer id);

	Hotel saveHotel(Hotel hotel);

	Hotel updateHotel(Hotel hotel, Integer id);
	
	void deleteHotel(Integer id);

}

package com.atsistemas.formacion.base.apihotels.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.service.HotelService;


@RestController
@RequestMapping("hotels")
public class HotelController {

	private HotelService service;

	public HotelController(HotelService service) {
		this.service = service;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Hotel> listHotels() {
		return service.listHotels();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Hotel findHotel(@PathVariable("id") Integer id) {
		return service.findHotel(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Hotel createHotel(@RequestBody Hotel hotel) {
		return service.saveHotel(hotel);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Hotel updateHotel(@RequestBody Hotel hotel, @PathVariable Integer id) {
		return service.updateHotel(hotel, id);
	}
	
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteHotel(@PathVariable("id") Integer id) {
		service.deleteHotel(id);
	}
	
	
	
	
}

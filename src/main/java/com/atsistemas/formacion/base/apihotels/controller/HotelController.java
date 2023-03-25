package com.atsistemas.formacion.base.apihotels.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.formacion.base.apihotels.DTO.in.CheckAvailabilityEntry;
import com.atsistemas.formacion.base.apihotels.DTO.in.CheckBookingsEntry;
import com.atsistemas.formacion.base.apihotels.DTO.out.BookingDto;
import com.atsistemas.formacion.base.apihotels.DTO.out.HotelDto;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.mapper.BookingMapper;
import com.atsistemas.formacion.base.apihotels.mapper.HotelMapper;
import com.atsistemas.formacion.base.apihotels.service.HotelService;


@RestController
@RequestMapping("hotels")
public class HotelController {

	private HotelService service;
	private HotelMapper mapper;
	private BookingMapper mapperBooking;

	public HotelController(HotelService service, HotelMapper mapper, BookingMapper mapperBooking) {
		this.service = service;
		this.mapper = mapper;
		this.mapperBooking = mapperBooking;
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
	public HotelDto createHotel(@RequestBody HotelDto hotel) {
		return mapper.mapToDto(service.saveHotel(mapper.mapToEntity(hotel)));
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public HotelDto updateHotel(@RequestBody HotelDto hotel, @PathVariable Integer id) {
		return mapper.mapToDto(service.updateHotel(mapper.mapToEntity(hotel), id));
	}
	
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteHotel(@PathVariable("id") Integer id) {
		service.deleteHotel(id);
	}
	
	@GetMapping(value = "/availability", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<HotelDto> checkAvailability(@RequestBody CheckAvailabilityEntry entry) {
		return service.checkAvailability(entry.getCheckIn(), entry.getCheckOut(), entry.getName(), entry.getCategory()).stream()
				.map(h -> mapper.mapToDto(h))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}/bookings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookingDto>> getBookingsByHotelInDateRange(@PathVariable(name = "id") Integer idHotel,@RequestBody CheckBookingsEntry entry) {
		List<Booking> res = service.getBookingsByHotelInDateRange(entry.getDateFrom(), entry.getDateTo(), idHotel);
		if(res != null) {
			return new ResponseEntity<>(res.stream()
					.map(b -> mapperBooking.mapToDto(b))
					.collect(Collectors.toList()),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}

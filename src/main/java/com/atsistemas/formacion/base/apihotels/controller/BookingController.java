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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.formacion.base.apihotels.DTO.out.BookingDto;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.mapper.BookingMapper;
import com.atsistemas.formacion.base.apihotels.service.BookingService;

@RestController
@RequestMapping("bookings")
public class BookingController {

	private BookingService service;
	private BookingMapper mapper;
	

	public BookingController(BookingService service, BookingMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BookingDto> listBookings() {
		return service.listBookings().stream()
				.map(b -> mapper.mapToDto(b))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingDto findBooking(@PathVariable("id") Integer id) {
		return mapper.mapToDto(service.findBooking(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto booking) {
		Booking res = service.saveBooking(mapper.mapToEntity(booking));
		if(res != null) {
			return new ResponseEntity<>(mapper.mapToDto(res),HttpStatus.CREATED);
		}else {
			/* we return code 422 if we have a null booking as this would mean that even though the data
			 * was properly received by the server, something was off and made the saving of the booking impossible
			 * for example having the initial date of the booking being after the last day of the booking.
			 */
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteBooking(@PathVariable("id") Integer id) {
		service.deleteBooking(id);
	}
}

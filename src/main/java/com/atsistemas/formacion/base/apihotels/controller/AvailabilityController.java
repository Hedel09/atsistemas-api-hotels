package com.atsistemas.formacion.base.apihotels.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.formacion.base.apihotels.DTO.in.OpenAvailabilityEntry;
import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.service.AvailabilityService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("availabilities")
public class AvailabilityController {

	private AvailabilityService service;

	public AvailabilityController(AvailabilityService service) {
		this.service = service;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Availability> listAvailabilities() {
		return service.listAvailabilities();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Availability findAvailability(@PathVariable("id") Integer id) {
		return service.findAvailability(id);
	}

//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public Availability createAvailability(@RequestBody Availability availability) {
//		return service.saveAvailability(availability);
//	}
	
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAvailability(@PathVariable("id") Integer id) {
		service.deleteAvailability(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<Availability> openAvailability(@RequestBody OpenAvailabilityEntry entry) {
		return service.openAvailability(entry.getIdHotel(), entry.getCheckIn(), entry.getCheckOut(), entry.getRooms());
	}
//	@RequestBody Integer idHotel, @RequestBody @JsonFormat(pattern="yyyy-MM-dd") LocalDate checkIn,
//	@RequestBody @JsonFormat(pattern="yyyy-MM-dd") LocalDate checkOut, @RequestBody Integer rooms
	
	
}

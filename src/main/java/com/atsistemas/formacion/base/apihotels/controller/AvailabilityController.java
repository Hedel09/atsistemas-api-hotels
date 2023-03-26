package com.atsistemas.formacion.base.apihotels.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.formacion.base.apihotels.DTO.in.OpenAvailabilityEntry;
import com.atsistemas.formacion.base.apihotels.DTO.out.AvailabilityDto;
import com.atsistemas.formacion.base.apihotels.mapper.AvailabilityMapper;
import com.atsistemas.formacion.base.apihotels.service.AvailabilityService;

@RestController
@RequestMapping("availabilities")
public class AvailabilityController {

	private AvailabilityService service;
	private AvailabilityMapper mapper;

	public AvailabilityController(AvailabilityService service, AvailabilityMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AvailabilityDto> listAvailabilities() {
		return service.listAvailabilities().stream()
				.map(a -> mapper.mapToDto(a))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AvailabilityDto findAvailability(@PathVariable("id") Integer id) {
		return mapper.mapToDto(service.findAvailability(id));
	}

	
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteAvailability(@PathVariable("id") Integer id) {
		service.deleteAvailability(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<AvailabilityDto> openAvailability(@RequestBody OpenAvailabilityEntry entry) {
		return service.openAvailability(entry.getIdHotel(), entry.getCheckIn(), entry.getCheckOut(), entry.getRooms()).stream()
				.map(a -> mapper.mapToDto(a))
				.collect(Collectors.toList());
	}
	
	
}

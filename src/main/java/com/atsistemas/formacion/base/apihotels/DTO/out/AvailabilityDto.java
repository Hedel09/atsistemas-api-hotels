package com.atsistemas.formacion.base.apihotels.DTO.out;

import java.time.LocalDate;


public class AvailabilityDto {

	private Integer id;
	
	private LocalDate date;
	
	private Integer rooms;
	
	private HotelDto hotel;
	

	public AvailabilityDto() {
	}

	public AvailabilityDto(Integer id, LocalDate date, Integer rooms, HotelDto hotel) {
		super();
		this.id = id;
		this.date = date;
		this.rooms = rooms;
		this.hotel = hotel;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	
	
	
	
	
	
	
}

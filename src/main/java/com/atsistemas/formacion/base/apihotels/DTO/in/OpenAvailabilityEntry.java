package com.atsistemas.formacion.base.apihotels.DTO.in;

import java.time.LocalDate;

import org.springframework.lang.NonNull;

public class OpenAvailabilityEntry {


	@NonNull
	private Integer idHotel;
	@NonNull
	private LocalDate checkIn;
	@NonNull
	private LocalDate checkOut;
	@NonNull
	private Integer rooms;
	
	public OpenAvailabilityEntry() {
	}

	public OpenAvailabilityEntry(Integer idHotel, LocalDate checkIn, LocalDate checkOut, Integer rooms) {
		super();
		this.idHotel = idHotel;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.rooms = rooms;
	}

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}
	
}

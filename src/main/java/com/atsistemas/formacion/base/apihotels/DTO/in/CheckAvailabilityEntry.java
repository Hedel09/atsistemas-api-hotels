package com.atsistemas.formacion.base.apihotels.DTO.in;

import java.time.LocalDate;

public class CheckAvailabilityEntry {

	private LocalDate checkIn;
	private LocalDate checkOut;
	private String name;
	private Double category;
	
	public CheckAvailabilityEntry() {
	}

	public CheckAvailabilityEntry(LocalDate checkIn, LocalDate checkOut, String name, Double category) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.name = name;
		this.category = category;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCategory() {
		return category;
	}

	public void setCategory(Double category) {
		this.category = category;
	}

	
	
}

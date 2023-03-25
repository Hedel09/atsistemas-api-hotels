package com.atsistemas.formacion.base.apihotels.DTO.out;

import java.time.LocalDate;


public class BookingDto {
	
	private Integer id;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private String email;
	private Integer idHotel;
	private HotelDto hotel;
	
	
	public BookingDto() {
	}


	public BookingDto(Integer id, LocalDate dateFrom, LocalDate dateTo, String email, HotelDto hotel) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.email = email;
		this.hotel = hotel;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDateFrom() {
		return dateFrom;
	}


	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}


	public LocalDate getDateTo() {
		return dateTo;
	}


	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public HotelDto getHotel() {
		return hotel;
	}


	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}


	public Integer getIdHotel() {
		return idHotel;
	}


	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	
	
	
}

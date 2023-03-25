package com.atsistemas.formacion.base.apihotels.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "availabilities")
public class Availability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "rooms")
	private Integer rooms;
	
	@Column(name = "id_hotel")
	private Integer idHotel;
	
//	@ManyToOne
//	@JoinColumn(name = "id_hotel", referencedColumnName= "id", nullable= false)
//	private Hotel hotel;

	public Availability() {
	}

	public Availability(Integer id, LocalDate date, Integer rooms, Integer idHotel) {
		super();
		this.id = id;
		this.date = date;
		this.rooms = rooms;
		this.idHotel = idHotel;
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

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}
	
	
	
	
	
	
}

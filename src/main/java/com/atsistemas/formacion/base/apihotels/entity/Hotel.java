package com.atsistemas.formacion.base.apihotels.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "hotels")
public class Hotel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name="category")
	private Double category;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_hotel", referencedColumnName= "id", nullable= false, insertable = false, updatable = false)
	private List<Availability> availabilities;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_hotel", referencedColumnName= "id", nullable= false, insertable = false, updatable = false)
	private List<Booking> bookings;
	
	public Hotel() {
	}
	
	

	public Hotel(Integer id, String name, Double category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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



	public List<Availability> getAvailabilities() {
		return availabilities;
	}



	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}



	public List<Booking> getBookings() {
		return bookings;
	}



	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	
	
	
	
	
	
}

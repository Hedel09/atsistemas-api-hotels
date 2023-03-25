package com.atsistemas.formacion.base.apihotels.DTO.out;


public class HotelDto {

	private Integer id;
	private String name;
	private Integer category;
	
	
	public HotelDto() {
	}
	
	

	public HotelDto(Integer id, String name, Integer category) {
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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}



}

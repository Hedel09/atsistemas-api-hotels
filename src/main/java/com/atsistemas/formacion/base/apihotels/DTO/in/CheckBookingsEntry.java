package com.atsistemas.formacion.base.apihotels.DTO.in;

import java.time.LocalDate;

public class CheckBookingsEntry {

	private LocalDate dateFrom;
	private LocalDate dateTo;
	
	public CheckBookingsEntry() {
	}

	public CheckBookingsEntry(LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
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


	
	
}

package com.atsistemas.formacion.base.apihotels.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atsistemas.formacion.base.apihotels.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	@Query(value = "SELECT b FROM Booking b where b.dateFrom >= :dateFrom and b.dateTo <= :dateTo and b.idHotel = :idHotel")
	List<Booking> getBookingsBetweenDatesByHotel(LocalDate dateFrom, LocalDate dateTo, Integer idHotel);
}

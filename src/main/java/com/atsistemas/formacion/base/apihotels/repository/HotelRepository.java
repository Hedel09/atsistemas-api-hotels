package com.atsistemas.formacion.base.apihotels.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atsistemas.formacion.base.apihotels.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>, JpaSpecificationExecutor<Hotel>{

//	@Query(value = "SELECT h FROM Hotel h where h.Availability.date between :checkIn and :checkOut")
//	List<Hotel> getAvailableHotels(LocalDate checkIn, LocalDate checkOut);
}

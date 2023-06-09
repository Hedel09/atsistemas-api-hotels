package com.atsistemas.formacion.base.apihotels.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atsistemas.formacion.base.apihotels.entity.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer>{
	
	Optional<Availability> findByDateAndIdHotel(LocalDate date, Integer idHotel);
	
	@Query(value = "SELECT a FROM Availability a where a.date between :checkIn and :checkOut and a.rooms>0")
	List<Availability> getAvailabilityBetweenDates(LocalDate checkIn, LocalDate checkOut);
	
	@Query(value = "SELECT a FROM Availability a where a.date between :checkIn and :checkOut and a.rooms>0 and a.idHotel = :idHotel")
	List<Availability> getAvailabilityBetweenDatesByHotel(LocalDate checkIn, LocalDate checkOut, Integer idHotel);
}

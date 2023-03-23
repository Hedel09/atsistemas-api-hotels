package com.atsistemas.formacion.base.apihotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.formacion.base.apihotels.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

}

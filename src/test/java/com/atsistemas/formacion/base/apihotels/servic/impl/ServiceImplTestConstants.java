package com.atsistemas.formacion.base.apihotels.servic.impl;

import java.time.LocalDate;

import com.atsistemas.formacion.base.apihotels.DTO.in.CheckAvailabilityEntry;
import com.atsistemas.formacion.base.apihotels.DTO.in.CheckBookingsEntry;
import com.atsistemas.formacion.base.apihotels.DTO.in.OpenAvailabilityEntry;
import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;

public class ServiceImplTestConstants {
	protected static final Integer DEFAULT_HOTEL_ID_1 = 1;
	protected static final Integer DEFAULT_HOTEL_ID_2 = 2;
	protected static final Integer DEFAULT_HOTEL_ID_3 = 3;
	
	protected static final Hotel TEST_HOTEL_1 = new Hotel(DEFAULT_HOTEL_ID_1, "Test 1", 3);
	protected static final Hotel TEST_HOTEL_2 = new Hotel(DEFAULT_HOTEL_ID_2, "Test 2", 5);
	protected static final Hotel TEST_HOTEL_3 = new Hotel(DEFAULT_HOTEL_ID_3, "Test 3", 4);
	
	
	protected static final Integer DEFAULT_AVAILABILITY_ID_1 = 1;
	protected static final Integer DEFAULT_AVAILABILITY_ID_2 = 2;
	protected static final Integer DEFAULT_AVAILABILITY_ID_3 = 3;
	
	protected static final Availability TEST_AVAILABILITY_1 = new Availability(DEFAULT_AVAILABILITY_ID_1, LocalDate.of(2024, 10, 23), 10, 1);
	protected static final Availability TEST_AVAILABILITY_2 = new Availability(DEFAULT_AVAILABILITY_ID_2, LocalDate.of(2024, 10, 24), 20, 1);
	protected static final Availability TEST_AVAILABILITY_3 = new Availability(DEFAULT_AVAILABILITY_ID_3, LocalDate.of(2024, 10, 25), 20, 1);
	
	protected static final CheckAvailabilityEntry CHECK_AVAILABILITY_ENTRY = new CheckAvailabilityEntry(LocalDate.of(2024, 10, 23), LocalDate.of(2024, 10, 25),null,null);
	protected static final CheckAvailabilityEntry CHECK_AVAILABILITY_ENTRY_WRONG_DATES = new CheckAvailabilityEntry(LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 23),null,null);
	
	protected static final OpenAvailabilityEntry OPEN_AVAILABILITY_ENTRY = new OpenAvailabilityEntry(1,  LocalDate.of(2024, 10, 23), LocalDate.of(2024, 10, 25), 20);
	protected static final OpenAvailabilityEntry OPEN_AVAILABILITY_ENTRY_WRONG_DATES = new OpenAvailabilityEntry(1,  LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 23), 20);
	
	
	protected static final Integer DEFAULT_BOOKING_ID_1 = 1;
	protected static final Integer DEFAULT_BOOKING_ID_2 = 2;
	protected static final Integer DEFAULT_BOOKING_ID_3 = 3;
	protected static final Booking TEST_BOOKING_1 = new Booking(DEFAULT_BOOKING_ID_1, LocalDate.of(2024, 10, 23), LocalDate.of(2024, 10, 25), "test1@mail.com", 1);
	protected static final Booking TEST_BOOKING_2 = new Booking(DEFAULT_BOOKING_ID_2, LocalDate.of(2024, 10, 24), LocalDate.of(2024, 10, 26), "test2@mail.com", 1);
	protected static final Booking TEST_BOOKING_3 = new Booking(DEFAULT_BOOKING_ID_3, LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 27), "test3@mail.com", 1);
	protected static final Booking TEST_BOOKING_WRONG_DATES = new Booking(DEFAULT_BOOKING_ID_1, LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 23), "test3@mail.com", 1);
	
	protected static final CheckBookingsEntry CHECK_BOOKING_ENTRY = new CheckBookingsEntry(LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 27));
	protected static final CheckBookingsEntry CHECK_BOOKING_ENTRY_WRONG_DATES = new CheckBookingsEntry(LocalDate.of(2024, 10, 27), LocalDate.of(2024, 10, 25));
}

package com.atsistemas.formacion.base.apihotels.servic.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.error.DateFormatException;
import com.atsistemas.formacion.base.apihotels.error.IncorrectDataException;
import com.atsistemas.formacion.base.apihotels.error.NoAvailabilityException;
import com.atsistemas.formacion.base.apihotels.error.NullParamsException;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.repository.BookingRepository;
import com.atsistemas.formacion.base.apihotels.repository.HotelRepository;
import com.atsistemas.formacion.base.apihotels.service.impl.AvailabilityServiceImpl;
import com.atsistemas.formacion.base.apihotels.service.impl.BookingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest extends ServiceImplTestConstants {
	
	private BookingServiceImpl bookingServiceImpl;
	
	@Mock private BookingRepository bookingRepositoryMock;

	@Mock private AvailabilityRepository availabilityRepositoryMock;

	@Mock private HotelRepository hotelRepositoryMock;

	@Mock private AvailabilityServiceImpl availabilityServiceImplMock;
	
	@BeforeEach
	public void setup() {
		bookingServiceImpl = new BookingServiceImpl(bookingRepositoryMock,
				availabilityRepositoryMock, hotelRepositoryMock, availabilityServiceImplMock);
	}
	
	//TEST LIST
	@Test
	public void testListBookings() {
		Mockito.when(bookingRepositoryMock.findAll())
		.thenReturn(List.of(TEST_BOOKING_1, TEST_BOOKING_2));
		
		List<Booking> listBookings = bookingServiceImpl.listBookings();
		Assertions.assertNotNull(listBookings);
		Assertions.assertTrue(listBookings.size() > 0);
		Assertions.assertEquals(2, listBookings.size());
		
		Mockito.verify(bookingRepositoryMock).findAll();
	}
	
	//TESTS FIND
	// · TEST CORRECT FIND BOOKING
	@Test
	public void testFindBooking() {
		Mockito.when(bookingRepositoryMock.findById(DEFAULT_BOOKING_ID_1))
		.thenReturn(Optional.of(TEST_BOOKING_1));
		
		Booking booking = bookingServiceImpl.findBooking(DEFAULT_BOOKING_ID_1);
		Assertions.assertNotNull(booking);
		Assertions.assertEquals(DEFAULT_BOOKING_ID_1, booking.getId());
		
		Mockito.verify(bookingRepositoryMock).findById(DEFAULT_BOOKING_ID_1);
	}
	
	// · TEST BOOKING NOT FOUND
	@Test
	public void testFindBookingEmpty() {
		Mockito.when(bookingRepositoryMock.findById(DEFAULT_BOOKING_ID_1))
		.thenReturn(Optional.empty());
		
		Assertions.assertThrows(IncorrectDataException.class,() -> bookingServiceImpl.findBooking(DEFAULT_BOOKING_ID_1));
		
		Mockito.verify(bookingRepositoryMock).findById(DEFAULT_BOOKING_ID_1);
	}
	
	// · TEST TRIED TO FIND A NULL ID BOOKING
	@Test
	public void testFindBookingNullId() {
		Mockito.when(bookingRepositoryMock.findById(null))
		.thenThrow(new IllegalArgumentException());
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> bookingServiceImpl.findBooking(null));
		
		Mockito.verify(bookingRepositoryMock).findById(null);
	}
	
	//TESTS SAVE
	// · TEST CORRECT SAVE
	@Test
	public void testSaveBooking() {
		Mockito.when(bookingRepositoryMock.save(TEST_BOOKING_1))
		.thenReturn(TEST_BOOKING_1);
		Mockito.when(hotelRepositoryMock.findById(1))
		.thenReturn(Optional.of(TEST_HOTEL_1));
		Mockito.when(availabilityRepositoryMock.getAvailabilityBetweenDates(TEST_BOOKING_1.getDateFrom(), TEST_BOOKING_1.getDateTo()))
		.thenReturn(List.of(TEST_AVAILABILITY_1, TEST_AVAILABILITY_2, TEST_AVAILABILITY_3));
		Mockito.when(availabilityRepositoryMock.save(Mockito.any()))
		.thenReturn(TEST_AVAILABILITY_1);
		
		Booking booking = bookingServiceImpl.saveBooking(TEST_BOOKING_1);
		Assertions.assertNotNull(booking);
		Assertions.assertNotNull(booking.getHotel());
		Assertions.assertEquals(1, booking.getId());
		Assertions.assertEquals(1, booking.getIdHotel());
		
		
		Mockito.verify(bookingRepositoryMock).save(TEST_BOOKING_1);
	}
	// · TEST SAVE WITH NULL BOOKING
	@Test
	public void testSaveNullBooking() {
		
		Assertions.assertThrows(NullParamsException.class,() -> bookingServiceImpl.saveBooking(null));
		Assertions.assertThrows(NullParamsException.class,() -> bookingServiceImpl.saveBooking(new Booking(null,null,null,null,null)));
		
		
	}
	 
	// · TEST SAVE WRONG DATES
	@Test
	public void testSaveBookingWrongDates() {
		
		Assertions.assertThrows(DateFormatException.class,() -> bookingServiceImpl.saveBooking(TEST_BOOKING_WRONG_DATES));
		
		
	}
	// · TEST SAVE NO AVAILABILITIES
	@Test
	public void testSaveBookingNoAvailabilities() {
		Mockito.when(availabilityRepositoryMock.getAvailabilityBetweenDates(TEST_BOOKING_1.getDateFrom(), TEST_BOOKING_1.getDateTo()))
		.thenReturn(List.of(TEST_AVAILABILITY_1));
		

		Assertions.assertThrows(NoAvailabilityException.class,() -> bookingServiceImpl.saveBooking(TEST_BOOKING_1));
		
		
		Mockito.verify(availabilityRepositoryMock).getAvailabilityBetweenDates(TEST_BOOKING_1.getDateFrom(), TEST_BOOKING_1.getDateTo());
	}
	
}

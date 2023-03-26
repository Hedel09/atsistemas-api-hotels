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

import com.atsistemas.formacion.base.apihotels.constants.ServiceImplTestConstants;
import com.atsistemas.formacion.base.apihotels.entity.Booking;
import com.atsistemas.formacion.base.apihotels.entity.Hotel;
import com.atsistemas.formacion.base.apihotels.error.DateFormatException;
import com.atsistemas.formacion.base.apihotels.error.IncorrectDataException;
import com.atsistemas.formacion.base.apihotels.error.NullParamsException;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.repository.BookingRepository;
import com.atsistemas.formacion.base.apihotels.repository.HotelRepository;
import com.atsistemas.formacion.base.apihotels.service.impl.HotelServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HotelServiceImplTest extends ServiceImplTestConstants {
	
	private HotelServiceImpl hotelServiceImpl;
	
	@Mock private BookingRepository bookingRepositoryMock;

	@Mock private AvailabilityRepository availabilityRepositoryMock;

	@Mock private HotelRepository hotelRepositoryMock;

	
	@BeforeEach
	public void setup() {
		hotelServiceImpl = new HotelServiceImpl(hotelRepositoryMock,
				availabilityRepositoryMock, bookingRepositoryMock);
	}
	
	//TEST LIST
	@Test
	public void testListHotels() {
		Mockito.when(hotelRepositoryMock.findAll())
		.thenReturn(List.of(TEST_HOTEL_1, TEST_HOTEL_2, TEST_HOTEL_3));
		
		List<Hotel> listHotels = hotelServiceImpl.listHotels();
		Assertions.assertNotNull(listHotels);
		Assertions.assertTrue(listHotels.size() > 0);
		Assertions.assertEquals(3, listHotels.size());
		
		Mockito.verify(hotelRepositoryMock).findAll();
	}
	
	//TESTS FIND
	// · TEST CORRECT FIND HOTEL
	@Test
	public void testFindHotel() {
		Mockito.when(hotelRepositoryMock.findById(DEFAULT_HOTEL_ID_1))
		.thenReturn(Optional.of(TEST_HOTEL_1));
		
		Hotel hotel = hotelServiceImpl.findHotel(DEFAULT_HOTEL_ID_1);
		Assertions.assertNotNull(hotel);
		Assertions.assertEquals(DEFAULT_BOOKING_ID_1, hotel.getId());
		
		Mockito.verify(hotelRepositoryMock).findById(DEFAULT_HOTEL_ID_1);
	}
	
	// · TEST BOOKING NOT FOUND
	@Test
	public void testFindHotelEmpty() {
		Mockito.when(hotelRepositoryMock.findById(DEFAULT_HOTEL_ID_1))
		.thenReturn(Optional.empty());
		
		Assertions.assertThrows(IncorrectDataException.class,() -> hotelServiceImpl.findHotel(DEFAULT_HOTEL_ID_1));
		
		Mockito.verify(hotelRepositoryMock).findById(DEFAULT_HOTEL_ID_1);
	}
	
	// · TEST TRIED TO FIND A NULL BOOKING
	@Test
	public void testFindHotelNullId() {
		Mockito.when(hotelRepositoryMock.findById(null))
		.thenThrow(new IllegalArgumentException());
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> hotelServiceImpl.findHotel(null));
		
		Mockito.verify(hotelRepositoryMock).findById(null);
	}
	//TESTS SAVE
	// · TEST CORRECT SAVE
	@Test
	public void testSaveHotel() {
		Mockito.when(hotelRepositoryMock.save(TEST_HOTEL_1))
		.thenReturn(TEST_HOTEL_1);
		
		Hotel hotel = hotelServiceImpl.saveHotel(TEST_HOTEL_1);
		Assertions.assertNotNull(hotel);
		Assertions.assertEquals(1, hotel.getId());
		
		Mockito.verify(hotelRepositoryMock).save(TEST_HOTEL_1);
	}
	
	// · TEST SAVE WITH NULL AVAILABILITY
	@Test
	public void testSaveHotelNull() {
		
		Assertions.assertThrows(NullParamsException.class,() -> hotelServiceImpl.saveHotel(null));
		Assertions.assertThrows(NullParamsException.class,() -> hotelServiceImpl.saveHotel(new Hotel(null,null,null)));
	}
	
	//TESTS UPDATE
	// · TEST CORRECT UPDATE
	@Test
	public void testUpdateHotel() {
		Mockito.when(hotelRepositoryMock.save(TEST_HOTEL_1))
		.thenReturn(TEST_HOTEL_1);
		
		Hotel hotel = hotelServiceImpl.updateHotel(TEST_HOTEL_1,DEFAULT_HOTEL_ID_1);
		Assertions.assertNotNull(hotel);
		Assertions.assertEquals(1, hotel.getId());
		
		Mockito.verify(hotelRepositoryMock).save(TEST_HOTEL_1);
	}
	
	// · TEST UPDATE WITH NULL HOTEL
	@Test
	public void testUpdateHotelNull() {
		
		Assertions.assertThrows(NullParamsException.class,() -> hotelServiceImpl.updateHotel(null,DEFAULT_HOTEL_ID_1));
		Assertions.assertThrows(NullParamsException.class,() -> hotelServiceImpl.updateHotel(new Hotel(null,null,null),DEFAULT_HOTEL_ID_1));
		Assertions.assertThrows(NullParamsException.class,() -> hotelServiceImpl.updateHotel(TEST_HOTEL_1,null));
		
	}
	
	//TESTS DELETE
	// · TEST TRIED TO DELETE A NULL ID HOTEL
	@Test
	public void testDeleteHotelNullId() {
		Mockito.doThrow(new IllegalArgumentException()).when(hotelRepositoryMock).deleteById(null);
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> hotelServiceImpl.deleteHotel(null));
		
		Mockito.verify(hotelRepositoryMock).deleteById(null);
	}
	
	//TESTS CHECK AVAILABILITY
	// · TEST CORRECT CHECK AVAILABILITY
	@Test
	public void testCheckAvailability() {
		
		Mockito.when(availabilityRepositoryMock.getAvailabilityBetweenDates(CHECK_AVAILABILITY_ENTRY.getCheckIn(),
				CHECK_AVAILABILITY_ENTRY.getCheckOut()))
		.thenReturn(List.of(TEST_AVAILABILITY_1, TEST_AVAILABILITY_2, TEST_AVAILABILITY_3));
		Mockito.when(hotelRepositoryMock.findById(DEFAULT_HOTEL_ID_1))
		.thenReturn(Optional.of(TEST_HOTEL_1));
		
		List<Hotel> listHotels = hotelServiceImpl.checkAvailability(CHECK_AVAILABILITY_ENTRY.getCheckIn(),
				CHECK_AVAILABILITY_ENTRY.getCheckOut(), CHECK_AVAILABILITY_ENTRY.getName(),
				CHECK_AVAILABILITY_ENTRY.getCategory());
		Assertions.assertNotNull(listHotels);
		Assertions.assertTrue(listHotels.size() > 0);
		Assertions.assertEquals(1, listHotels.size());

		Mockito.verify(availabilityRepositoryMock).getAvailabilityBetweenDates(CHECK_AVAILABILITY_ENTRY.getCheckIn(),
				CHECK_AVAILABILITY_ENTRY.getCheckOut());
		Mockito.verify(hotelRepositoryMock).findById(Mockito.anyInt());
	}
	
	// · TEST CHECK AVAILABILITY WRONG DATES
	@Test
	public void testCheckAvailabilityWrongDates() {
		
		Assertions.assertThrows(DateFormatException.class,() -> hotelServiceImpl.checkAvailability(CHECK_AVAILABILITY_ENTRY_WRONG_DATES.getCheckIn(),
				CHECK_AVAILABILITY_ENTRY_WRONG_DATES.getCheckOut(), CHECK_AVAILABILITY_ENTRY_WRONG_DATES.getName(),
				CHECK_AVAILABILITY_ENTRY_WRONG_DATES.getCategory()));
	}
	
	// · TEST CHECK AVAILABILITY PAST DATES
	@Test
	public void testCheckAvailabilityPASTDates() {
		
		Assertions.assertThrows(DateFormatException.class,() -> hotelServiceImpl.checkAvailability(CHECK_AVAILABILITY_ENTRY_PAST_DATES.getCheckIn(),
				CHECK_AVAILABILITY_ENTRY_PAST_DATES.getCheckOut(), CHECK_AVAILABILITY_ENTRY_PAST_DATES.getName(),
				CHECK_AVAILABILITY_ENTRY_PAST_DATES.getCategory()));
	}
	
	// · TEST CHECK AVAILABILITY NULL PARAMS
	@Test
	public void testCheckAvailabilityNullParams() {
		Assertions.assertThrows(NullParamsException.class,() -> hotelServiceImpl.checkAvailability(null,null,null,null));
	}
	
	//TESTS GET BOOKINGS
	// · TEST CORRECT GET BOOKINGS
	@Test
	public void testGetBookingsByHotelInDateRange() {
		
		Mockito.when(bookingRepositoryMock.getBookingsBetweenDatesByHotel(CHECK_BOOKING_ENTRY.getDateFrom(),
				CHECK_BOOKING_ENTRY.getDateTo(), DEFAULT_HOTEL_ID_1))
		.thenReturn(List.of(TEST_BOOKING_3));
		
		List<Booking> listBookings = hotelServiceImpl.getBookingsByHotelInDateRange(CHECK_BOOKING_ENTRY.getDateFrom(),
				CHECK_BOOKING_ENTRY.getDateTo(), DEFAULT_HOTEL_ID_1);
		Assertions.assertNotNull(listBookings);
		Assertions.assertTrue(listBookings.size() > 0);
		Assertions.assertEquals(1, listBookings.size());

		Mockito.verify(bookingRepositoryMock).getBookingsBetweenDatesByHotel(CHECK_BOOKING_ENTRY.getDateFrom(),
				CHECK_BOOKING_ENTRY.getDateTo(), DEFAULT_HOTEL_ID_1);
	}
	
	// · TEST GET BOOKINGS WRONG DATES
	@Test
	public void testGetBookingsByHotelInDateRangeWrongDates() {
		
		Assertions.assertThrows(DateFormatException.class,() -> hotelServiceImpl.getBookingsByHotelInDateRange(CHECK_BOOKING_ENTRY_WRONG_DATES.getDateFrom(),
				CHECK_BOOKING_ENTRY_WRONG_DATES.getDateTo(), DEFAULT_HOTEL_ID_1));
	}
	
	// · TEST GET BOOKINGS PAST DATES
	@Test
	public void testGetBookingsByHotelInDateRangePastDates() {
		
		Assertions.assertThrows(DateFormatException.class,() -> hotelServiceImpl.getBookingsByHotelInDateRange(CHECK_BOOKING_ENTRY_PAST_DATES.getDateFrom(),
				CHECK_BOOKING_ENTRY_PAST_DATES.getDateTo(), DEFAULT_HOTEL_ID_1));
	}
	
	// · TEST GET BOOKINGS NULL PARAMS
	@Test
	public void testGetBookingsByHotelInDateRangeNullParams() {
		Assertions.assertThrows(NullParamsException.class,() -> hotelServiceImpl.getBookingsByHotelInDateRange(null,null, null));
	}
	
}

package com.atsistemas.formacion.base.apihotels.servic.impl;

import java.time.LocalDate;
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
import com.atsistemas.formacion.base.apihotels.entity.Availability;
import com.atsistemas.formacion.base.apihotels.error.DateFormatException;
import com.atsistemas.formacion.base.apihotels.error.IncorrectDataException;
import com.atsistemas.formacion.base.apihotels.error.NullParamsException;
import com.atsistemas.formacion.base.apihotels.repository.AvailabilityRepository;
import com.atsistemas.formacion.base.apihotels.service.impl.AvailabilityServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AvailabilityServiceImplTest extends ServiceImplTestConstants {
	
	private AvailabilityServiceImpl availabilityServiceImpl;
	
	@Mock private AvailabilityRepository availabilityRepositoryMock;
	
	@BeforeEach
	public void setup() {
		availabilityServiceImpl = new AvailabilityServiceImpl(availabilityRepositoryMock);
	}
	//TEST LIST
	@Test
	public void testListAvailabilities() {
		Mockito.when(availabilityRepositoryMock.findAll())
		.thenReturn(List.of(TEST_AVAILABILITY_1, TEST_AVAILABILITY_2));
		
		List<Availability> listAvailabilities = availabilityServiceImpl.listAvailabilities();
		Assertions.assertNotNull(listAvailabilities);
		Assertions.assertTrue(listAvailabilities.size() > 0);
		Assertions.assertEquals(2, listAvailabilities.size());
		
		Mockito.verify(availabilityRepositoryMock).findAll();
	}
	//TESTS FIND
	// · TEST CORRECT FIND AVAILABILITY
	@Test
	public void testFindAvailability() {
		Mockito.when(availabilityRepositoryMock.findById(DEFAULT_AVAILABILITY_ID_1))
		.thenReturn(Optional.of(TEST_AVAILABILITY_1));
		
		Availability availability = availabilityServiceImpl.findAvailability(DEFAULT_AVAILABILITY_ID_1);
		Assertions.assertNotNull(availability);
		Assertions.assertEquals(DEFAULT_AVAILABILITY_ID_1, availability.getId());
		
		Mockito.verify(availabilityRepositoryMock).findById(DEFAULT_AVAILABILITY_ID_1);
	}
	
	// · TEST AVAILABILITY NOT FOUND
	@Test
	public void testFindAvailabilityEmpty() {
		Mockito.when(availabilityRepositoryMock.findById(DEFAULT_AVAILABILITY_ID_1))
		.thenReturn(Optional.empty());
		
		Assertions.assertThrows(IncorrectDataException.class,() -> availabilityServiceImpl.findAvailability(DEFAULT_AVAILABILITY_ID_1));
		
		Mockito.verify(availabilityRepositoryMock).findById(DEFAULT_AVAILABILITY_ID_1);
	}
	
	// · TEST TRIED TO FIND A NULL ID AVAILABILITY
	@Test
	public void testFindAvailabilityNoId() {
		Mockito.when(availabilityRepositoryMock.findById(null))
		.thenThrow(new IllegalArgumentException());
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> availabilityServiceImpl.findAvailability(null));
		
		Mockito.verify(availabilityRepositoryMock).findById(null);
	}
	
	
	//TESTS DELETE
	// · TEST TRIED TO DELETE A NULL ID AVAILABILITY
	@Test
	public void testDeleteAvailabilityNullId() {
		Mockito.doThrow(new IllegalArgumentException()).when(availabilityRepositoryMock).deleteById(null);
		
		Assertions.assertThrows(IllegalArgumentException.class,() -> availabilityServiceImpl.deleteAvailability(null));
		
		Mockito.verify(availabilityRepositoryMock).deleteById(null);
	}
	
	//TESTS OPEN AVAILABILITY
	// · TEST CORRECT OPEN AVAILABILITY
	@Test
	public void testOpenAvailability() {
		Mockito.when(availabilityRepositoryMock.findByDate(LocalDate.of(2024, 10, 23))).thenReturn(Optional.of(TEST_AVAILABILITY_1));
		Mockito.when(availabilityRepositoryMock.findByDate(LocalDate.of(2024, 10, 24))).thenReturn(Optional.of(TEST_AVAILABILITY_2));
		Mockito.when(availabilityRepositoryMock.findByDate(LocalDate.of(2024, 10, 25))).thenReturn(Optional.of(TEST_AVAILABILITY_3));
		Mockito.when(availabilityRepositoryMock.save(TEST_AVAILABILITY_1))
		.thenReturn(TEST_AVAILABILITY_1);
		Mockito.when(availabilityRepositoryMock.save(TEST_AVAILABILITY_2))
		.thenReturn(TEST_AVAILABILITY_2);
		Mockito.when(availabilityRepositoryMock.save(TEST_AVAILABILITY_3))
		.thenReturn(TEST_AVAILABILITY_3);
		
		
		List<Availability> listAvailabilities = availabilityServiceImpl.openAvailability(
				OPEN_AVAILABILITY_ENTRY.getIdHotel(),
				OPEN_AVAILABILITY_ENTRY.getCheckIn(),
				OPEN_AVAILABILITY_ENTRY.getCheckOut(),
				OPEN_AVAILABILITY_ENTRY.getRooms());
		Assertions.assertNotNull(listAvailabilities);
		Assertions.assertTrue(listAvailabilities.size() > 0);
		Assertions.assertEquals(3, listAvailabilities.size());
		
		Mockito.verify(availabilityRepositoryMock).findByDate(LocalDate.of(2024, 10, 23));
		Mockito.verify(availabilityRepositoryMock).findByDate(LocalDate.of(2024, 10, 24));
		Mockito.verify(availabilityRepositoryMock).findByDate(LocalDate.of(2024, 10, 25));
		Mockito.verify(availabilityRepositoryMock).save(TEST_AVAILABILITY_1);
		Mockito.verify(availabilityRepositoryMock).save(TEST_AVAILABILITY_2);
		Mockito.verify(availabilityRepositoryMock).save(TEST_AVAILABILITY_3);
		
		
	}
	
	// · TEST OPEN AVAILABILITY WRONG DATES
	@Test
	public void testOpenAvailabilityWrongDates() {
		Assertions.assertThrows(DateFormatException.class,() -> availabilityServiceImpl.openAvailability(
				OPEN_AVAILABILITY_ENTRY_WRONG_DATES.getIdHotel(),
				OPEN_AVAILABILITY_ENTRY_WRONG_DATES.getCheckIn(),
				OPEN_AVAILABILITY_ENTRY_WRONG_DATES.getCheckOut(),
				OPEN_AVAILABILITY_ENTRY_WRONG_DATES.getRooms()));
	}
	
	// · TEST OPEN AVAILABILITY PAST DATES
	@Test
	public void testOpenAvailabilityPastDates() {
		Assertions.assertThrows(DateFormatException.class,() -> availabilityServiceImpl.openAvailability(
				OPEN_AVAILABILITY_ENTRY_PAST_DATES.getIdHotel(),
				OPEN_AVAILABILITY_ENTRY_PAST_DATES.getCheckIn(),
				OPEN_AVAILABILITY_ENTRY_PAST_DATES.getCheckOut(),
				OPEN_AVAILABILITY_ENTRY_PAST_DATES.getRooms()));
	}
	
	// · TEST OPEN AVAILABILITY NULL PARAMS
	@Test
	public void testOpenAvailabilityNullParams() {
		Assertions.assertThrows(NullParamsException.class,() -> availabilityServiceImpl.openAvailability(
				null,
				OPEN_AVAILABILITY_ENTRY.getCheckIn(),
				OPEN_AVAILABILITY_ENTRY.getCheckOut(),
				OPEN_AVAILABILITY_ENTRY.getRooms()));
	}
	
}

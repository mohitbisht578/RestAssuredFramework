package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.Booking;
import com.qa.gorest.pojo.Booking.BookingDates;

public class CreateBookingTest extends BaseTest {
	
	@BeforeMethod
	public void createBookingSetUp() {
		restClient = new RestClient(prop, baseURI);
	}

	@DataProvider
	public Object[][] getBookingTestData() {
		
		return new Object[][] {
			
			{"Tom", "Jerry", 170, true, "2023-01-01", "2023-08-01", "Dinner"},
			{"Ram", "Jerry", 170, true, "2023-01-01", "2023-08-01", "Dinner"},
			{"Team", "Jerry", 170, true, "2023-01-01", "2023-08-01", "Dinner"}
				
		};
	}
	
	@Test(dataProvider="getBookingTestData")
	public void createBooking(String firstName, String lastName, int totalPrice, Boolean depositPaid,
			String checkin, String checkout, String additionalNeeds) {
		
		BookingDates bookingDates = new BookingDates(checkin, checkout);
	    Booking booking = new Booking(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
		
		
		//BookingDates bookingDates = new BookingDates("2023-01-01", "2023-08-1");
		//Booking booking = new Booking("Tom", "Raju", 150, true, bookingDates, "Dinner");
		Integer bookingId = restClient.post(BOOKING_ENDPOINT, "JSON" , booking, false, true)
		.then().log().all()
		.assertThat().statusCode(APIHttpStatus.OK_200.getCode())
		.extract().path("bookingid");
		
		System.out.println("Booking id: " + bookingId);
		
		//2. Get Booking id
		restClient.get(BOOKING_ENDPOINT + "/" + bookingId, false, true)
		.then().log().all();
	}

}

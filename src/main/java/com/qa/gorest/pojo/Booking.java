package com.qa.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

	private String firstname;
	private String lastname;
	private Integer totalprice;
	private Boolean depositpaid;	
	private BookingDates bookingdates;
	private String additionalneeds;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class BookingDates {
		
		private String checkin;
		private String checkout;
	}
}

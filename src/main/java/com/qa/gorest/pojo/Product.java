package com.qa.gorest.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	public Product(String title, double price, String description, String category, String image) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
	}

	//Rating inner class
    @Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Rating {
		private double rate;
		private int count;
	}

}
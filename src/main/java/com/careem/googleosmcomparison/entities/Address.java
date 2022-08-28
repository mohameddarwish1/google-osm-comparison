package com.careem.googleosmcomparison.entities;

import lombok.Data;
import lombok.ToString;

@Data
public class Address {

	private String neighborhood;
	private String suburb;
	private String city;
	private String state;
	private String country;
	private String country_code;
	
	
}

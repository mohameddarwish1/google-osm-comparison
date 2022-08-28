package com.careem.googleosmcomparison.entities;

import lombok.Data;

@Data
public class OsmPlace {

	private String place_id;
	private String type;
	private String source;
	private double lng;
	private double lat;
	private int distance;
	private String name;
	private String display_name;
	private Address address;
	
}

package com.careem.googleosmcomparison.entities.nearby.google;

import java.util.ArrayList;

import lombok.Data;

@Data
public class NearbyGoogleResult {

	private String status;
	private ArrayList<Object> html_attributions;
	private ArrayList<Result> results;
	private String next_page_token;
	private String error_message;


}

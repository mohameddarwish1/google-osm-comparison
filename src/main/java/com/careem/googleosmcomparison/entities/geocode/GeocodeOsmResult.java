package com.careem.googleosmcomparison.entities.geocode;

import java.util.ArrayList;

import com.careem.googleosmcomparison.entities.OsmPlace;

import lombok.Data;


@Data
public class GeocodeOsmResult {

	private ArrayList<OsmPlace> results;
	private int count;

}

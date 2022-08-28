package com.careem.googleosmcomparison.entities.nearby.osm;

import java.util.ArrayList;

import com.careem.googleosmcomparison.entities.OsmPlace;

import lombok.Data;

@Data
public class NearbyOsmResult {

	private ArrayList<OsmPlace> results;
	private int count;

}

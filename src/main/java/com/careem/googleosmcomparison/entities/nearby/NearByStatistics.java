package com.careem.googleosmcomparison.entities.nearby;

import com.careem.googleosmcomparison.entities.nearby.google.NearbyGoogleResult;
import com.careem.googleosmcomparison.entities.nearby.osm.NearbyOsmResult;

import lombok.Data;

@Data
public class NearByStatistics {

	private NearbyOsmResult nearbyOsmResult;
	private NearbyGoogleResult nearbyGoogleResult;
	private String lat;
	private String lng;

}

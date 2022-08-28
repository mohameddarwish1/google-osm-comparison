package com.careem.googleosmcomparison.external;

import com.careem.googleosmcomparison.entities.geocode.GeocodeOsmResult;
import com.careem.googleosmcomparison.entities.nearby.google.NearbyGoogleResult;
import com.careem.googleosmcomparison.entities.nearby.osm.NearbyOsmResult;

public interface ExternalCall {

	public  NearbyOsmResult getOsmNearByLocations(String lat, String lng);
	
	public  NearbyGoogleResult getGoogleNearByLocations(String lat, String lng);
	
	public  GeocodeOsmResult getOsmGeoCodedAddress(String lat, String lng);
	
}

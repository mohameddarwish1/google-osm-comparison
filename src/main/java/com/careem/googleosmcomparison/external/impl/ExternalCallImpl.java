package com.careem.googleosmcomparison.external.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.careem.googleosmcomparison.entities.geocode.GeocodeOsmResult;
import com.careem.googleosmcomparison.entities.nearby.google.NearbyGoogleResult;
import com.careem.googleosmcomparison.entities.nearby.osm.NearbyOsmResult;
import com.careem.googleosmcomparison.external.ExternalCall;


@Service
public class ExternalCallImpl implements ExternalCall{
	
	
	public  NearbyOsmResult getOsmNearByLocations(String lat, String lng){
		
	    final String uri = "http://prod-green-geocoder-service.eba-vib4mcak.eu-west-1.elasticbeanstalk.com/v1/nearby?"
	    		+ "lat="+lat+"&lng="+lng+"&radius=1000&lang=en;";

	    RestTemplate restTemplate = new RestTemplate();
	    NearbyOsmResult result = restTemplate.getForObject(uri, NearbyOsmResult.class);

	    return result;
	}
	
	public  NearbyGoogleResult getGoogleNearByLocations(String lat, String lng)
	{
	    final String uri = "http://third-party-location-service.careem-internal.com/api/nearby?location="
	    		+lat+","+lng+"&radius=1000&lang=en";

	    RestTemplate restTemplate = new RestTemplate();
	    NearbyGoogleResult result = restTemplate.getForObject(uri, NearbyGoogleResult.class);

	    return result;
	}
	
	
	public  GeocodeOsmResult getOsmGeoCodedAddress(String lat, String lng)
	{
	    final String uri = "http://prod-green-geocoder-service.eba-vib4mcak.eu-west-1.elasticbeanstalk.com/"
	    		+ "v1/reverse?lat="+lat+"&lng="+lng+"&radius=1000&limit=10&lang=en";

	    RestTemplate restTemplate = new RestTemplate();
	    GeocodeOsmResult result = restTemplate.getForObject(uri, GeocodeOsmResult.class);

	    return result;
	}

}

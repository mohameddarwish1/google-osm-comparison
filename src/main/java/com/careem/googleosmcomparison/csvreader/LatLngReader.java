package com.careem.googleosmcomparison.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.careem.googleosmcomparison.entities.latlnginput.InputLatLng;

@Service
public class LatLngReader {

	public  List<InputLatLng> getInput() {
		{
			String line = "";
			String splitBy = ",";
			List<InputLatLng> inputLatLngs = new ArrayList<>();
			
			try {
				// parsing a CSV file into BufferedReader class constructor
				BufferedReader br = new BufferedReader(new FileReader("/Users/mohamed.darwish/Downloads/comparison/google-osm-comparison/latlong.csv"));
				while ((line = br.readLine()) != null) // returns a Boolean value
				{
					String[] latLong = line.split(splitBy); // use comma as separator

					InputLatLng inputLatLng = new InputLatLng();
					inputLatLng.setPlaceName(latLong[0]);
					inputLatLng.setLat(latLong[1]);
					inputLatLng.setLng(latLong[2]);
					
					inputLatLngs.add(inputLatLng);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return inputLatLngs;
		}

	}

}

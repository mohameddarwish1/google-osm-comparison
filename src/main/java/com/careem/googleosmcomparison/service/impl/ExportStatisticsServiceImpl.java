package com.careem.googleosmcomparison.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careem.googleosmcomparison.csvreader.LatLngReader;
import com.careem.googleosmcomparison.entities.geocode.GeocodeOsmResult;
import com.careem.googleosmcomparison.entities.latlnginput.InputLatLng;
import com.careem.googleosmcomparison.entities.nearby.NearByStatistics;
import com.careem.googleosmcomparison.entities.nearby.google.NearbyGoogleResult;
import com.careem.googleosmcomparison.entities.nearby.osm.NearbyOsmResult;
import com.careem.googleosmcomparison.excelgenerator.ExcelUtil;
import com.careem.googleosmcomparison.external.ExternalCall;
import com.careem.googleosmcomparison.service.ExportStatisticsService;

@Service
public class ExportStatisticsServiceImpl implements ExportStatisticsService {

	@Autowired
	LatLngReader latLngReader;

	@Autowired
	ExcelUtil excelUtil;

	@Autowired
	ExternalCall externalCall;

	public void exportGeocodeOsmGoogleDelta() {

		List<InputLatLng> inputLatLngs = latLngReader.getInput();

		Workbook workbook = excelUtil.getWorkBook();
		Sheet geoCodeSheet = excelUtil.makeGeoCodeDeltaSheet(workbook);

		int count = 1;

		for (InputLatLng inputLatLng : inputLatLngs) {

			GeocodeOsmResult geoCodeOsmResult = externalCall.getOsmGeoCodedAddress(inputLatLng.getLat(),
					inputLatLng.getLng());


			excelUtil.makeGeoCodeDeltaHeader(workbook, geoCodeSheet);

			count = excelUtil.makeGeoCodeDeltaContent(workbook, geoCodeSheet, geoCodeOsmResult, inputLatLng.getLat(),
					inputLatLng.getLng(), count);

		}
		excelUtil.writeToExcel(workbook, "GeoCode");
	}

	public void exportNearbyOsmGoogleCount() {

		List<InputLatLng> inputLatLngs = latLngReader.getInput();

		int count = 1;
		Workbook workbook = excelUtil.getWorkBook();
		Sheet nearBySheet = excelUtil.makeNearBySheet(workbook);

		for (InputLatLng inputLatLng : inputLatLngs) {

			NearbyGoogleResult nearbyGoogleResult = externalCall.getGoogleNearByLocations(inputLatLng.getLat(),
					inputLatLng.getLng());

			NearbyOsmResult nearByOsmResult = externalCall.getOsmNearByLocations(inputLatLng.getLat(),
					inputLatLng.getLng());
			

			excelUtil.makeNearByHeader(workbook, nearBySheet);

			NearByStatistics nearByStatistics = new NearByStatistics();

			nearByStatistics.setLat(inputLatLng.getLat());

			nearByStatistics.setLng(inputLatLng.getLng());

			nearByStatistics.setNearbyGoogleResult(nearbyGoogleResult);

			nearByStatistics.setNearbyOsmResult(nearByOsmResult);

			count = excelUtil.makeNearbyContent(workbook, nearBySheet, nearByStatistics, count);

		}
		excelUtil.writeToExcel(workbook,"NearBy");
	}
}

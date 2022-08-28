package com.careem.googleosmcomparison.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careem.googleosmcomparison.service.ExportStatisticsService;

@RestController
public class StatisticsImporter {
	
	@Autowired
	ExportStatisticsService exportStatisticsService;	
	
	@GetMapping("/export")
	String generat() {
		
		exportStatisticsService.exportNearbyOsmGoogleCount();
		exportStatisticsService.exportGeocodeOsmGoogleDelta();

		return "OKAY";
	}
}

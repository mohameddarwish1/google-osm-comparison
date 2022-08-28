package com.careem.googleosmcomparison.excelgenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.careem.googleosmcomparison.entities.OsmPlace;
import com.careem.googleosmcomparison.entities.geocode.GeocodeOsmResult;
import com.careem.googleosmcomparison.entities.nearby.NearByStatistics;
import com.careem.googleosmcomparison.entities.nearby.google.Result;
import com.careem.googleosmcomparison.excelgenerator.util.Util;


@Service
public class ExcelUtil {

	public void writeToExcel(Workbook workbook, String fileName) {

		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + fileName+".xlsx";
		try {

			FileOutputStream outputStream = new FileOutputStream(fileLocation);

			workbook.write(outputStream);

			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Workbook getWorkBook() {

		return new XSSFWorkbook();
	}

	public Sheet makeGeoCodeDeltaSheet(Workbook workbook) {

		Sheet sheet = workbook.createSheet("GeoCodeDelta");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 10000);

		return sheet;
	}

	public Sheet makeNearBySheet(Workbook workbook) {

		Sheet sheet = workbook.createSheet("NearByStats");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 10000);
		sheet.setColumnWidth(3, 10000);
		sheet.setColumnWidth(4, 10000);
		sheet.setColumnWidth(5, 10000);

		return sheet;
	}

	public void makeGeoCodeDeltaHeader(Workbook workbook, Sheet geoCodeDeltaSheet) {

		CellStyle headerStyle = getHeaderStyle(workbook);
		Row header = geoCodeDeltaSheet.createRow(0);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("OSM LAT/LNG");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("GOOGLE LAT/LNG");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("PLACE NAME");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(3);
		headerCell.setCellValue("DISTANCE");
		headerCell.setCellStyle(headerStyle);
	}

	public void makeNearByHeader(Workbook workbook, Sheet nearBySheet) {

		CellStyle headerStyle = getHeaderStyle(workbook);

		Row header = nearBySheet.createRow(0);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("LAT/LNG");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(1);
		headerCell.setCellValue("OSM-NEARBY");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(2);
		headerCell.setCellValue("COUNT");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(3);
		headerCell.setCellValue("GOOGLE-NEARBY");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(4);
		headerCell.setCellValue("COUNT");
		headerCell.setCellStyle(headerStyle);

		headerCell = header.createCell(5);
		headerCell.setCellValue("DIFF");
		headerCell.setCellStyle(headerStyle);

	}

	public int makeGeoCodeDeltaContent(Workbook workbook, Sheet geoCodeDeltaSheet,
			GeocodeOsmResult geocodeOsmResult, String googleLat, String googleLng , int count) {

		CellStyle contentStyle = getContentStyle(workbook);
		for (OsmPlace osmPlace : geocodeOsmResult.getResults()) {

			Row row = geoCodeDeltaSheet.createRow(count);

			Cell osmLatLng = row.createCell(0);
			osmLatLng.setCellValue(osmPlace.getLat() + "/" + osmPlace.getLng());
			osmLatLng.setCellStyle(contentStyle);

			Cell googleLatLng = row.createCell(1);
			googleLatLng.setCellValue(googleLat + "/" + googleLng);
			googleLatLng.setCellStyle(contentStyle);

			Cell placeName = row.createCell(2);
			placeName.setCellValue(osmPlace.getDisplay_name());
			placeName.setCellStyle(contentStyle);

			Cell distance = row.createCell(3);
			distance.setCellValue(Util.distance(Double.valueOf(osmPlace.getLat()),Double.valueOf(osmPlace.getLng())
					,Double.valueOf(googleLat),Double.valueOf(googleLng),'K'));
			distance.setCellStyle(contentStyle);

			count++;
		}
		
		return count;
	}
	
	public int makeNearbyContent(Workbook workbook, Sheet nearBySheet,
			NearByStatistics nearByStatistics , int count) {

		CellStyle contentStyle = getContentStyle(workbook);

			Row row = nearBySheet.createRow(count);

			Cell inputLatLng = row.createCell(0);
			inputLatLng.setCellValue(nearByStatistics.getLat() + "/" + nearByStatistics.getLng());
			inputLatLng.setCellStyle(contentStyle);

			Cell osmNearByResult = row.createCell(1);
			osmNearByResult.setCellValue(getNearbyOsmResult(nearByStatistics.getNearbyOsmResult().getResults()));
			osmNearByResult.setCellStyle(contentStyle);

			Cell osmNearByCount = row.createCell(2);
			osmNearByCount.setCellValue(nearByStatistics.getNearbyOsmResult().getCount());
			osmNearByCount.setCellStyle(contentStyle);
			
			Cell googleNearByResult = row.createCell(3);
			googleNearByResult.setCellValue(getNearbyGoogleResult(nearByStatistics.getNearbyGoogleResult().getResults()));
			googleNearByResult.setCellStyle(contentStyle);
			
			Cell googleNearByCount = row.createCell(4);
			googleNearByCount.setCellValue(nearByStatistics.getNearbyGoogleResult().getResults().size());
			googleNearByCount.setCellStyle(contentStyle);
			
			Cell difference = row.createCell(5);
			difference.setCellValue(nearByStatistics.getNearbyOsmResult().getCount() - nearByStatistics.getNearbyGoogleResult().getResults().size());
			difference.setCellStyle(contentStyle);
			
			count++;
			
			return count;

	}

	private String getNearbyOsmResult(ArrayList<OsmPlace> results) {
		String result = "";
		for(OsmPlace osmPlace : results) {
			result+= osmPlace;
			result+="\n";
			result+="\n--------------------------------";
		}
		return result;
	}

	private String getNearbyGoogleResult(ArrayList<Result> arrayList) {
		String result = "";
		for(Result googleResult : arrayList) {
			result+= googleResult;
			result+="\n";
			result+="\n--------------------------------";
		}
		return result;
	}
	
	
	private CellStyle getHeaderStyle(Workbook workbook) {

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		headerStyle.setFont(font);

		return headerStyle;
	}

	private CellStyle getContentStyle(Workbook workbook) {

		CellStyle style = workbook.createCellStyle();
		style.setWrapText(true);

		return style;
	}



}

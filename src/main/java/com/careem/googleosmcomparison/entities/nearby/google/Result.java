package com.careem.googleosmcomparison.entities.nearby.google;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Result{
    private Geometry geometry;
    private String icon;
    private String name;
    private OpeningHours opening_hours;
    private ArrayList<Photo> photos;
    private String place_id;
    private ArrayList<String> types;
    private String vicinity;

}

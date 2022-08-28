package com.careem.googleosmcomparison.entities.nearby.google;

import java.util.ArrayList;

import lombok.Data;
import lombok.ToString;

@Data
public class Photo{
    private String photo_reference;
    private String height;
    private String width;
    private ArrayList<Object> html_attributions;
   
}

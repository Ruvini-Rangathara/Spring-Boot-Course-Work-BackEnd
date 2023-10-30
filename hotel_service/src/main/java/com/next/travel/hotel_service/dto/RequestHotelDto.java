package com.next.travel.hotel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestHotelDto {
    private String hotelCode;
    private String name;
    private String category;
    private int starRate;
    private String location;
    private String email;
    private String contactNo;
    private String petsAllowedOrNot;
    private String cancellationCriteria;
    private List<byte[]> imageList = new ArrayList<>();
    private double opt1_price;
    private double opt2_price;
    private double opt3_price;
    private double opt4_price;
}

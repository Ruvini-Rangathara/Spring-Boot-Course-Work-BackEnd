package com.next.travel.hotel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDto implements Serializable {
    private String hotelCode;
    private String name;
    private String category;
    private int startRate;
    private String location;
    private String email;
    private List<String> contactNo = new ArrayList<>();
    private String petsAllowedOrNot;
    private String cancellationCriteria;
    private List<byte[]> imageList = new ArrayList<>();
    private List<DiscountDto> discountList = new ArrayList<>();
    private List<OptionDto> optionsList = new ArrayList<>();

}

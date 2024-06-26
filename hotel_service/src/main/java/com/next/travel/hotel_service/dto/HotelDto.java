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
    private int starRate;
    private String location;
    private String email;
    private String contactNo;
    private String petsAllowedOrNot;
    private String cancellationCriteria;
    private List<byte[]> imageList = new ArrayList<>();
    private OptionDto optionDto1;
    private OptionDto optionDto2;
    private OptionDto optionDto3;
    private OptionDto optionDto4;

}

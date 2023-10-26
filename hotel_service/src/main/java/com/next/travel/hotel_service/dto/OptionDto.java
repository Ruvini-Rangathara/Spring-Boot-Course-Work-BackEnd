package com.next.travel.hotel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionDto implements Serializable {
    private int optionNumber;
    private double price;
    private int capacity;
    private int id;
    @ToString.Exclude
    private HotelDto hotelDto;
}

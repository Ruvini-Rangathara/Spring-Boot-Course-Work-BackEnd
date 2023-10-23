package com.next.travel.hotel_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptionDto implements Serializable {
    private String optionId;
    private int optionNumber;
    private double price;
    private HotelDto hotel;
}

package com.next.travel.hotel_service.dto;

import com.next.travel.hotel_service.entity.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountDto implements Serializable {

    private String code;
    private String range;
    private double rate;
    private HotelEntity hotel;
}

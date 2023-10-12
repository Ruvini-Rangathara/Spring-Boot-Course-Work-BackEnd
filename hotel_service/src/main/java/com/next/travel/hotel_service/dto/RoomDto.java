package com.next.travel.hotel_service.dto;


import com.next.travel.hotel_service.entity.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto implements Serializable {
    private String roomId;
    private String category;
    private int capacity;
    private double price;
    private String availability;
    private HotelEntity hotel;
}

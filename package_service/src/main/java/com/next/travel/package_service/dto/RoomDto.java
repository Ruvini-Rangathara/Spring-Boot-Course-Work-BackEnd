package com.next.travel.package_service.dto;

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
    private HotelDto hotel;
}

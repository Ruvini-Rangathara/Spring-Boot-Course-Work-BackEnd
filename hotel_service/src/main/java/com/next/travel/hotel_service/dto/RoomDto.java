package com.next.travel.hotel_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {
    private String roomId;
    private String category;
    private String capacity;
    private double price;
    private String availability;

}

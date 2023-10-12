package com.next.travel.hotel_service.dto;

import com.next.travel.hotel_service.entity.DiscountEntity;
import com.next.travel.hotel_service.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDto implements Serializable {
    private String hotelCode;
    private String name;
    private String category;
    private String location;
    private String email;
    private String[] contactNo;
    private String petsAllowedOrNot;
    private String cancellationCriteria;
    private List<Byte[]> imageList;
}

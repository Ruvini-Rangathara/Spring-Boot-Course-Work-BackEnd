package com.next.travel.hotel_service.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDto {
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

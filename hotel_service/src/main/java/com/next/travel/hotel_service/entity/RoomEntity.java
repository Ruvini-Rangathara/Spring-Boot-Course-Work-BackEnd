package com.next.travel.hotel_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "room")
public class RoomEntity implements Serializable {
    @Id
    private String roomId;
    private String category;
    private int capacity;
    private double price;
    private String availability;

    // Define the many-to-one relationship with HotelEntity
    @ManyToOne
    private HotelEntity hotel;
}

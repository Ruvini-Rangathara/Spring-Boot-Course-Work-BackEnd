package com.next.travel.hotel_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "discount")
public class DiscountEntity {
    @Id
    private String code;
    private String range;
    private double rate;

    // Define the many-to-one relationship with HotelEntity
    @ManyToOne
    private HotelEntity hotel;
}

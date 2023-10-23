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
@Entity(name = "option")
public class OptionEntity implements Serializable {
    @Id
    private String optionId;
    private int optionNumber;
    private double price;
    // Define the many-to-one relationship with HotelEntity
    @ManyToOne
    private HotelEntity hotel;
}

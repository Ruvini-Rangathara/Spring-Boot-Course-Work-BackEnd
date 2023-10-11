package com.next.travel.hotel_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "hotel")
public class HotelEntity {
    @Id
    private String hotelCode;
    private String name;
    private String category;
    private String location;
    private String email;

    @ElementCollection
    private List<String> contactNo;
    private String petsAllowedOrNot;
    private String cancellationCriteria;

    @ElementCollection
    @CollectionTable(name = "image_data", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "image", columnDefinition = "BLOB")
    private List<Byte[]> imageList;

    // Define the one-to-many relationship with DiscountEntity
    @OneToMany(mappedBy = "hotel") // "hotel" refers to the property name in DiscountEntity
    private List<DiscountEntity> discounts;

    // Define the one-to-many relationship with RoomEntity
    @OneToMany(mappedBy = "hotel") // "hotel" refers to the property name in RoomEntity
    private List<RoomEntity> rooms;
}
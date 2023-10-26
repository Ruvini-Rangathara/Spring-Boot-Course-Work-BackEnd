package com.next.travel.hotel_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "hotel")
public class HotelEntity implements Serializable {
    @Id
    private String hotelCode;
    private String name;
    private String category;
    private int startRate;
    private String location;
    private String email;

    @ElementCollection
    private List<String> contactNo;
    private String petsAllowedOrNot;
    private String cancellationCriteria;

    @ElementCollection
    @CollectionTable(name = "image_data", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private List<byte[]> imageList = new ArrayList<>();

    // Define the one-to-many relationship with DiscountEntity
    @OneToMany(mappedBy = "hotel") // "hotel" refers to the property name in DiscountEntity
    private List<DiscountEntity> discounts = new ArrayList<>();

//    // Define the one-to-many relationship with RoomEntity
//    @OneToMany(mappedBy = "hotel") // "hotel" refers to the property name in RoomEntity
//    private List<OptionEntity> rooms = new ArrayList<>();
//

//    @ElementCollection
//    @CollectionTable(name = "options", joinColumns = @JoinColumn(name = "entity_id"))
//    private List<Double> optionList = new ArrayList<>();


    @OneToMany(cascade ={ CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<OptionEntity> optionsList = new ArrayList<>();
}

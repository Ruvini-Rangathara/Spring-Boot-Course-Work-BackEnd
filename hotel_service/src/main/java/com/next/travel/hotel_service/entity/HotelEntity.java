package com.next.travel.hotel_service.entity;

import com.next.travel.hotel_service.dto.OptionDto;
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
    private int starRate;
    private String location;
    private String email;

    private String contactNo;
    private String petsAllowedOrNot;
    private String cancellationCriteria;

    @ElementCollection
    @CollectionTable(name = "image_data", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private List<byte[]> imageList = new ArrayList<>();

    private OptionDto optionDto1;
    private OptionDto optionDto2;
    private OptionDto optionDto3;
    private OptionDto optionDto4;
}

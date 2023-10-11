package com.next.travel.vehicle_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "vehicle")
public class VehicleEntity {
    @Id
    private String vehicleId;
    private String brand;
    private String category;
    private String fuelType;
    private String hybridOrNon;
    private String fuelUsage;
    private int seatCapacity;
    private String vehicleType;
    private String transmissionType;  //auto or manual
    private String availability;
    private String remark;

    @ElementCollection
    @CollectionTable(name = "image_data", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "image", columnDefinition = "BLOB")
    private List<Byte[]> imageList;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

}

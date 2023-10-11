package com.next.travel.vehicle_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "driver")
public class DriverEntity {

    @Id
    private String driverId;
    private String name;
    private String contactNo;
    @Lob
    private byte[] licenseFront;
    @Lob
    private byte[] licenseBack;

    @OneToOne(mappedBy = "driver")
    private VehicleEntity vehicle;
}

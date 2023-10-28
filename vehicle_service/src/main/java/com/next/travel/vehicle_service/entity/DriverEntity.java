package com.next.travel.vehicle_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "driver")
public class DriverEntity {

    @Id
    private String driverId;
    private String name;
    private String contactNo;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] licenseFront;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] licenseBack;

    @ToString.Exclude
    @OneToOne(mappedBy = "driver")
    private VehicleEntity vehicle;
}

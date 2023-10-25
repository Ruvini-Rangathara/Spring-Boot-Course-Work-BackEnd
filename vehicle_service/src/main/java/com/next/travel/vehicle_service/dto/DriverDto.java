package com.next.travel.vehicle_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDto {
    private String driverId;
    private String name;
    private String contactNo;
    private byte[] licenseFront;
    private byte[] licenseBack;

    @ToString.Exclude
    private VehicleDto vehicleDto;

}

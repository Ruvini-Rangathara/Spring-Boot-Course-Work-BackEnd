package com.next.travel.package_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto {
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
    private List<byte[]> images;

    private DriverDto driverDto;

}

package com.next.travel.package_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageVehicleDto {
    private String packageId;
    private String vehicleId;
}

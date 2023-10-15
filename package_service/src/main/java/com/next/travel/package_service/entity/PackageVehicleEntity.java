package com.next.travel.package_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageVehicleEntity {

    @Id
    private String packageId;
    @Id
    private String vehicleId;

}


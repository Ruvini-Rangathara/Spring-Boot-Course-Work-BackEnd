package com.next.travel.package_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverDto {
    private String driverId;
    private String name;
    private String contactNo;
    private byte[] licenseFront;
    private byte[] licenseBack;

}

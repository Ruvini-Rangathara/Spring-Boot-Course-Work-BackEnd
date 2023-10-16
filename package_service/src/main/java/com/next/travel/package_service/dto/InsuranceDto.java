package com.next.travel.package_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsuranceDto {
    private String id;
    private double perOnePerson;
    private String description;
    private double lowerRange;
    private double upperRange;

}

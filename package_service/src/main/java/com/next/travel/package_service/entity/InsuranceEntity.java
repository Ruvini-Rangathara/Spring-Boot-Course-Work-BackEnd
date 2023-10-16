package com.next.travel.package_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsuranceEntity {
    @Id
    private String id;
    private double perOnePerson;
    private String description;
    private double lowerRange;
    private double upperRange;

}

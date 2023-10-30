package com.next.travel.package_service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionEntity {
    @Id
    private String promotionId;
    private double lowerRange;
    private double upperRange;
    private double rate;
    private Date exDate;
    private String availability;

}

package com.next.travel.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionDto {
    private String promotionId;
    private double lowerRange;
    private double upperRange;
    private double rate;
    private Date exDate;
    private String availability;
}

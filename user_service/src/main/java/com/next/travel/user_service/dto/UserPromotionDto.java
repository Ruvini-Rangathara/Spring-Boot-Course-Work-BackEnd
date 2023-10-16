package com.next.travel.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPromotionDto {
    private int id;
    private String username;
    private String promotionId;
    private String availability;

}

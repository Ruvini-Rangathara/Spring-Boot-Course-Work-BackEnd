package com.next.travel.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "promotion")
public class PromotionEntity {
    @Id
    private String promotionId;
    private double lowerRange;
    private double upperRange;
    private double rate;
    private Date exDate;
    private String availability;

    @OneToMany(mappedBy = "promotion")
    private List<UserPromotionEntity> userPromotions = new ArrayList<>();
}

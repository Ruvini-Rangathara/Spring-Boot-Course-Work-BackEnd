package com.next.travel.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserPromotionEntity {
    @Id
    private int id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private PromotionEntity promotion;

    private String availability;
}

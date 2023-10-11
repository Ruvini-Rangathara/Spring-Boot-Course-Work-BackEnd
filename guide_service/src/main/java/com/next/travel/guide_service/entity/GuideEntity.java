package com.next.travel.guide_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class GuideEntity {
    @Id
    private String guideId;
    private String name;
    private String address;
    private int age;
    private String gender;
    private String contactNo;
    private byte[] photo;
    private byte[] nicFrontImage;
    private byte[] nicBackImage;
    private byte[] guidIdFrontImage;
    private byte[] guideIdBackImage;
    private String experience;
    private double manDayValue;
    private String remark;

}

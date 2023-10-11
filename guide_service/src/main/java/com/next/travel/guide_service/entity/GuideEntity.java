package com.next.travel.guide_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
    @Lob
    private byte[] photo;
    @Lob
    private byte[] nicFrontImage;
    @Lob
    private byte[] nicBackImage;
    @Lob
    private byte[] guidIdFrontImage;
    @Lob
    private byte[] guideIdBackImage;
    private String experience;
    private double manDayValue;
    private String remark;

}

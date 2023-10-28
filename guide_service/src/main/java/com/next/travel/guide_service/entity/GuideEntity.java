package com.next.travel.guide_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "guide")
public class GuideEntity {
    @Id
    private String guideId;
    private String name;
    private String address;
    private int age;
    private String gender;
    private String contactNo;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] nicFrontImage;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] nicBackImage;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] guidIdFrontImage;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] guideIdBackImage;
    private String experience;
    private double manDayValue;
    private String remark;

}

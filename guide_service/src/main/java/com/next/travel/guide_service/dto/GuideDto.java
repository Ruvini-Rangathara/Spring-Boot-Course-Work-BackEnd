package com.next.travel.guide_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuideDto {
    private String guideId;
    private String name;
    private String address;
    private int age;
    private String gender;
    private String contactNo;
    @ToString.Exclude
    private byte[] photo;
    @ToString.Exclude
    private byte[] nicFrontImage;
    @ToString.Exclude
    private byte[] nicBackImage;
    @ToString.Exclude
    private byte[] guidIdFrontImage;
    @ToString.Exclude
    private byte[] guideIdBackImage;
    private String experience;
    private double manDayValue;
    private String remark;


}

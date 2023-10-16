package com.next.travel.package_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private byte[] photo;
    private byte[] nicFrontImage;
    private byte[] nicBackImage;
    private byte[] guidIdFrontImage;
    private byte[] guideIdBackImage;
    private String experience;
    private double manDayValue;
    private String remark;


}

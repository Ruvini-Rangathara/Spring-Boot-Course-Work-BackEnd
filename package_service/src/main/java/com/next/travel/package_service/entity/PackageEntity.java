package com.next.travel.package_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageEntity {
    @Id
    private String packageId;

    private String category;
    private String travelArea;
    private String withPetsOrNo;
    private String needAGuideOrNo;
    private String selectedDateTime;
    private double packageValue;
    private String duration;
    private int noOfNights;
    private int noOfDays;
    private Date startDate;
    private Date endDate;
    private int headCount;
    private int noOfChildren;
    private int noOfAdults;
    private double paidValue;
    private List<byte[]> paymentSlip;
    private String remark;

    private String guideId;
    private String userId;
}
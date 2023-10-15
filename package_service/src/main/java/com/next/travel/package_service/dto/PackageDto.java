package com.next.travel.package_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageDto {
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
    private List<byte[]> paymentSlip = new ArrayList<>();
    private String remark;

    private String guideId;
    private String userId;
    private List<String> vehicleId = new ArrayList<>();
    private List<String> roomId = new ArrayList<>();
}

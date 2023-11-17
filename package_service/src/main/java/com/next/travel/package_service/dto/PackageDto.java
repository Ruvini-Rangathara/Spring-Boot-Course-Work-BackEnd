package com.next.travel.package_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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
    private int noOfNights;
    private int noOfDays;
    private Date startDate;
    private Date endDate;
    private int headCount;
    private int noOfChildren;
    private int noOfAdults;
    private double paidValue;

    private byte[] paymentSlip1;

    private byte[] paymentSlip2;

    private String claimedOrNot;
    private double netTotal;
    private String remark;

    private double insuranceValue;
    private double discount;
    private double promotion;

    private double opt1_price;
    private double opt2_price;
    private double opt3_price;
    private double opt4_price;

    private int opt1_count;
    private int opt2_count;
    private int opt3_count;
    private int opt4_count;

    private String guideId;
    private String username;
    private String vehicleId;
    private String hotelCode;

    private String status;

}

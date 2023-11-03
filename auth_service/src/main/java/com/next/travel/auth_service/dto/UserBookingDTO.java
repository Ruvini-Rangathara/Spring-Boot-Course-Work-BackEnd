package com.next.travel.auth_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.next.travel.auth_service.dto.util.Status;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBookingDTO {
    private String bookingId;
    private String customerId;
    private String packageId;
    private String guideId;
    private String hotelOptionId;
    private String vehicleId;
    private int noOfChildren;
    private int noOfAdults;
    private LocalDateTime date;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private BigDecimal downPayment;
}

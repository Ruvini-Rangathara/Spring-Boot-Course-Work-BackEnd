package com.next.travel.package_service.controller;

import org.springframework.beans.factory.annotation.Value;

public class PackageApi {

    @Value("${guide-service-endpoint}")
    private String guideDataEndpoint;

    @Value("${hotel-service-endpoint}")
    private String hotelDataEndpoint;

    @Value("${vehicle-service-endpoint}")
    private String vehicleDataEndpoint;

    @Value("${user-service-endpoint}")
    private String userDataEndpoint;

}

package com.next.travel.package_service.controller;

import org.springframework.beans.factory.annotation.Value;

public class PackageApi {

    @Value("${hotel-service-endpoint}")
    private String hotelDataEndpoint;
}

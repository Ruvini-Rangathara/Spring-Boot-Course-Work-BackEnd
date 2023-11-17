package com.next.travel.package_service.controller;

import com.next.travel.package_service.dto.*;
import com.next.travel.package_service.exception.InvalidException;
import com.next.travel.package_service.service.PackageService;
import com.next.travel.package_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/v1/package")
@CrossOrigin(origins = "http://localhost:63342")
public class PackageApi {

    private final PackageService packageService;
    @Value("${guide-service-endpoint}")
    private String guideDataEndpoint;
    @Value("${hotel-service-endpoint}")
    private String hotelDataEndpoint;
    @Value("${vehicle-service-endpoint}")
    private String vehicleDataEndpoint;
    @Value("${user-service-endpoint}")
    private String userDataEndpoint;

    @Autowired
    public PackageApi(PackageService packageService) {
        this.packageService = packageService;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPackage(@RequestPart ("package") PackageDto packageDto,
                                        @RequestPart("slip1") byte[] slip1,
                                        @RequestPart("slip2") byte[] slip2) {

        packageDto.setPaymentSlip1(slip1);
        packageDto.setPaymentSlip2(slip2);
        packageService.save(packageDto);

        try {
            validatePackageData(packageDto);
            System.out.println("validated in backend");
            if (packageService.existById(packageDto.getPackageId())) {
                packageService.save(packageDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Package not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> findPackage(@RequestHeader String id) {
        System.out.println("package controller -> get package : " + id);
        boolean isExists = packageService.existById(id);
        if (!isExists) return ResponseEntity.badRequest().body("Package not found !");
        PackageDto packageDto = packageService.searchById(id);
        return ResponseEntity.ok(packageDto);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePackage(@RequestPart ("package") PackageDto packageDto,
                                           @RequestPart("slip1") byte[] slip1,
                                           @RequestPart("slip2") byte[] slip2) {

        packageDto.setPaymentSlip1(slip1);
        packageDto.setPaymentSlip2(slip2);
        validatePackageData(packageDto);
        packageService.update(packageDto);

        try {
            validatePackageData(packageDto);
            System.out.println("validated in backend");
            if (packageService.existById(packageDto.getPackageId())) {
                packageService.save(packageDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Package not found");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePackage(@RequestHeader("id") String id) {
        System.out.println("Package Controller -> delete Package");
        if (!Pattern.compile("^P\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid package id");
        try {
            packageService.delete(id);
            return ResponseEntity.ok().body("Package deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("Package Controller -> getAll");
        List<PackageDto> allPackages = packageService.getAll();
        System.out.println(allPackages.size());
        if (allPackages.isEmpty()) return ResponseEntity.ok().body("");
        System.out.println("done");
        return ResponseEntity.ok().body(allPackages);

    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getNewPackageID() {
        String packageId = packageService.getNewId();
        System.out.println("get new id :  " + packageId);

        return ResponseEntity.ok(packageId);
    }


    @GetMapping("/check/")
    public ResponseEntity<?> existsByPackageId(@RequestHeader String id) {
        boolean isExists = packageService.existById(id);
        System.out.println("isExists = " + isExists);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(false);
    }


    @GetMapping(path = "/guide", produces = MediaType.APPLICATION_JSON_VALUE)
    GuideDto getFullProfileGuide(@RequestParam String guideId) {

        //Method -1 - with RestTemplate(legacy)
//        RestTemplate restTemplate = new RestTemplate();
//        CustomerOrder initialCustomer = restTemplate.getForObject(customerDataEndpoint + customerId, CustomerOrder.class);
//        return orderService.getFullProfileData(initialCustomer);

        //Method -2 - with WebClient(modern)
        WebClient webClient = WebClient.create(guideDataEndpoint + guideId);
        Mono<GuideDto> responseGuide = webClient.get().retrieve() // fetch the data
                .bodyToMono(GuideDto.class);
        return packageService.getFullProfileDataOfGuide(responseGuide.block());
    }

    @GetMapping(path = "/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VehicleDto> getFullProfileVehicle(@RequestParam List<String> vehicleIdList) {
        List<VehicleDto> list = new ArrayList<>();
        for (String id : vehicleIdList) {
            WebClient webClient = WebClient.create(vehicleDataEndpoint + id);
            Mono<VehicleDto> responseVehicle = webClient.get().retrieve() // fetch the data
                    .bodyToMono(VehicleDto.class);
            list.add(packageService.getFullProfileDataOfVehicle(responseVehicle.block()));
        }
        return list;
    }


    private void validatePackageData(PackageDto packageDto) throws RuntimeException {
        if (!Pattern.compile("^P\\d{3,}$").matcher(packageDto.getPackageId()).matches()) {
            throw new InvalidException("Invalid id type!");

//        } else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(packageDto.getPackageValue())).matches()) {
//            throw new InvalidException("Invalid package value!");

//        } else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(packageDto.getPaidValue())).matches()) {
//            throw new InvalidException("Invalid paid value!");

        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfNights())).matches()) {
            throw new InvalidException("Invalid No Of Nights!");

        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfDays())).matches()) {
            throw new InvalidException("Invalid No Of Days!");
        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getHeadCount())).matches()) {
            throw new InvalidException("Invalid head count!");
        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfChildren())).matches()) {
            throw new InvalidException("Invalid no Of Children!");
        } else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfAdults())).matches()) {
            throw new InvalidException("Invalid No Of Adults!");
        }

    }

    @GetMapping("/getByStatus")
    public ResponseEntity<?> getAllByStatus(@RequestHeader String status) {
        System.out.println("Package Controller -> getAll by status");
        List<PackageDto> allPackages = packageService.getAllByStatus(status);
        System.out.println(allPackages.size());
        if (allPackages.isEmpty()) return ResponseEntity.ok().body("");
        System.out.println("done");
        return ResponseEntity.ok().body(allPackages);

    }

}

package com.next.travel.vehicle_service.controller;

import com.next.travel.vehicle_service.dto.DriverDto;
import com.next.travel.vehicle_service.exception.InvalidException;
import com.next.travel.vehicle_service.service.DriverService;
import com.next.travel.vehicle_service.service.VehicleService;
import com.next.travel.vehicle_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverApi {

    private final DriverService driverService;
    private final VehicleService vehicleService;

    @Autowired
    public DriverApi(DriverService driverService, VehicleService vehicleService) {
        this.driverService = driverService;
        this.vehicleService = vehicleService;
    }


    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse> saveDriver(@RequestPart("licenseFront") byte[] img1,
                                                       @RequestPart("licenseBack") byte[] img2,
                                                       @RequestPart("driver") DriverDto driverDto){

        System.out.println("Driver Controller -> " + driverDto);

        driverDto.setLicenseFront(img1);
        driverDto.setLicenseBack(img2);
        try {

            validateDriverData(driverDto);
            System.out.println("validated");


            return new ResponseEntity<>(new StandardResponse(201, "Driver was saved!", driverDto.toString()), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StandardResponse(400, "Driver was not saved!", driverDto.toString()), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDriver(@RequestHeader("id") String id) {
        System.out.println("Driver Controller -> delete Driver");
        if (!Pattern.compile("^D\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid driver id");
        try {
            boolean deleted = driverService.delete(id);
            return deleted ? ResponseEntity.ok().body("Driver deleted successfully!") : ResponseEntity.ok().body("Driver not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("Driver Controller -> getAll");
        List<DriverDto> allDrivers = driverService.getAll();
        System.out.println(allDrivers.size());
        if (allDrivers.isEmpty()) return ResponseEntity.ok().body("No drivers found");
        System.out.println("done");
        return ResponseEntity.ok().body(allDrivers);

    }

    @GetMapping("/check/")
    public ResponseEntity<?> existsById(@RequestHeader String id) {
        boolean isExists = driverService.existById(id);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getDriverByID(@RequestHeader String id) {
        System.out.println("DriverController -> getDriverByID: " + id);
        boolean isExists = driverService.existById(id);
        if (!isExists) return ResponseEntity.badRequest().body("Driver not found !");
        DriverDto driverDto = driverService.searchById(id);
        return ResponseEntity.ok(driverDto);
    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getOngoingID() {
        String lastDriverId = driverService.getLastId();
        return ResponseEntity.ok(lastDriverId);
    }

    @PatchMapping(value = "/update" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateVehicle(
            @RequestPart("licenseFront") byte[] license1,
            @RequestPart("licenseBack") byte[] license2,
            @RequestPart("driver") DriverDto driverDto,
            @RequestPart("driver_id") String driver_id,
            @RequestPart("vehicle_id") String vehicle_id)
    {
        System.out.println("Patch -> " + driver_id);
        driverDto.setLicenseFront(license1);
        driverDto.setLicenseBack(license2);
        try {
            validateDriverData(driverDto);
//            System.out.println("validated");
            if (driverService.existById(driverDto.getDriverId())) {
//                System.out.println("exists");
                driverDto.setVehicleDto(vehicleService.searchById(vehicle_id));
                driverService.save(driverDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Driver not exists");

        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private void validateDriverData(DriverDto driverDto) throws RuntimeException {
        if (!Pattern.compile("^([a-zA-Z]+( [a-zA-Z]+)*)$").matcher(driverDto.getName()).matches()) {
            throw new InvalidException("Invalid name type!");

        } else if (!(Pattern.compile("^D\\d{3,}$").matcher(String.valueOf(driverDto.getDriverId())).matches())) {
            throw new InvalidException("invalid id");

        } else if (!Pattern.compile("^\\d{10}$").matcher(driverDto.getContactNo()).matches()) {
            throw new InvalidException("Invalid contact number!");
        }
    }

}

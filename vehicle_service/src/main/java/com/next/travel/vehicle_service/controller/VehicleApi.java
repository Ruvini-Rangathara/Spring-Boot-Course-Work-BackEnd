package com.next.travel.vehicle_service.controller;


import com.next.travel.vehicle_service.dto.VehicleDto;
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
@RequestMapping("/api/v1/vehicle")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class VehicleApi {

    private final VehicleService vehicleService;
    private final DriverService driverService;

    @Autowired
    public VehicleApi(VehicleService vehicleService, DriverService driverService) {
        this.vehicleService = vehicleService;
        this.driverService = driverService;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StandardResponse> saveVehicle(@RequestPart("vehicle_img1") byte[] vehicle_img1, @RequestPart("vehicle_img2") byte[] vehicle_img2, @RequestPart("vehicle_img3") byte[] vehicle_img3, @RequestPart("vehicle_img4") byte[] vehicle_img4, @RequestPart("vehicle_img5") byte[] vehicle_img5, @RequestPart("vehicle") VehicleDto vehicleDto, @RequestPart("driver_id") String driver_id) {

        System.out.println("VehicleController -> " + vehicleDto);

        vehicleDto.getImages().add(vehicle_img1);
        vehicleDto.getImages().add(vehicle_img2);
        vehicleDto.getImages().add(vehicle_img3);
        vehicleDto.getImages().add(vehicle_img4);
        vehicleDto.getImages().add(vehicle_img5);
        try {

            validateVehicleData(vehicleDto);
            System.out.println("validated");


            vehicleDto.setDriver(driverService.searchById(driver_id));
            vehicleService.save(vehicleDto);
            return new ResponseEntity<>(new StandardResponse(201, "Vehicle was saved!", vehicleDto.toString()), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StandardResponse(400, "Vehicle was not saved!", vehicleDto.toString()), HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteVehicle(@RequestHeader("id") String id) {
        System.out.println("VehicleController -> deleteVehicle");
        if (!Pattern.compile("^V\\d{3,}$").matcher(id).matches())
            return ResponseEntity.badRequest().body("Invalid vehicle id");
        try {
            boolean deleted = vehicleService.delete(id);
            return deleted ? ResponseEntity.ok().body("Vehicle deleted successfully!") : ResponseEntity.ok().body("Vehicle not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        System.out.println("Vehicle Controller -> getAll");
        List<VehicleDto> allVehicles = vehicleService.getAll();
        System.out.println(allVehicles.size());
        if (allVehicles.isEmpty()) return ResponseEntity.ok().body("No vehicles found");
        System.out.println("done");
        return ResponseEntity.ok().body(allVehicles);

    }

    @GetMapping("/check/")
    public ResponseEntity<?> existsByVehicleId(@RequestHeader String vehicle_id) {
        boolean isExists = vehicleService.existById(vehicle_id);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(false);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getVehicleByVehicleID(@RequestHeader String vehicle_id) {
        System.out.println("VehicleController -> getVehicleByVehicleID: " + vehicle_id);
        boolean isExists = vehicleService.existById(vehicle_id);
        if (!isExists) return ResponseEntity.badRequest().body("Vehicle not found !");
        VehicleDto vehicleDto = vehicleService.searchById(vehicle_id);
        return ResponseEntity.ok(vehicleDto);
    }

    @GetMapping("/get/lastId")
    public ResponseEntity<?> getOngoingID() {
        String lastVehicleId = vehicleService.getLastId();
        return ResponseEntity.ok(lastVehicleId);
    }

    @PatchMapping(value = "/update" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateVehicle(
            @RequestPart("vehicle_img1") byte[] vehicle_img1,
            @RequestPart("vehicle_img2") byte[] vehicle_img2,
            @RequestPart("vehicle_img3") byte[] vehicle_img3,
            @RequestPart("vehicle_img4") byte[] vehicle_img4,
            @RequestPart("vehicle_img5") byte[] vehicle_img5,
            @RequestPart("vehicle") VehicleDto vehicleDto,
            @RequestPart("driver_id") String driver_id) {
        System.out.println("Patch -> " + vehicleDto);
        vehicleDto.getImages().add(vehicle_img1);
        vehicleDto.getImages().add(vehicle_img2);
        vehicleDto.getImages().add(vehicle_img3);
        vehicleDto.getImages().add(vehicle_img4);
        vehicleDto.getImages().add(vehicle_img5);
        try {
            validateVehicleData(vehicleDto);
//            System.out.println("validated");
            if (vehicleService.existById(vehicleDto.getVehicleId())) {
//                System.out.println("exists");
                vehicleDto.setDriver(driverService.searchById(driver_id));
                vehicleService.save(vehicleDto);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body("Vehicle not exists");

        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private void validateVehicleData(VehicleDto vehicleDto) throws RuntimeException {
        if (!Pattern.compile("^V\\d{3,}$").matcher(vehicleDto.getVehicleId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^[1-9]\\\\d*$").matcher(String.valueOf(vehicleDto.getSeatCapacity())).matches()) {
            throw new InvalidException("Invalid capacity!");
        }
    }

}

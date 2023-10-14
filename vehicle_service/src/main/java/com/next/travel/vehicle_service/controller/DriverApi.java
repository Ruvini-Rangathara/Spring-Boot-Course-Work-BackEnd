package com.next.travel.vehicle_service.controller;

import com.next.travel.vehicle_service.dto.DriverDto;
import com.next.travel.vehicle_service.dto.VehicleDto;
import com.next.travel.vehicle_service.exception.InvalidException;
import com.next.travel.vehicle_service.service.DriverService;
import com.next.travel.vehicle_service.service.VehicleService;
import com.next.travel.vehicle_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverApi {

    private final DriverService driverService;

    @Autowired
    public DriverApi(DriverService driverService) {
        this.driverService = driverService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addDriver(@RequestBody DriverDto driverDto) {
        validateDriverData(driverDto);
        driverService.save(driverDto);
        return new ResponseEntity<>(new StandardResponse(201, "Driver was saved!", driverDto.toString()), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findDriver(@PathVariable String id) {
        return new ResponseEntity<>(new StandardResponse(200, "Driver was found!", driverService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateVehicle(@RequestParam String id, @RequestBody DriverDto driverDto) {
        validateDriverData(driverDto);
        driverService.update(driverDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Driver Data!", driverDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable String id) {
        driverService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Driver Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllDriver() {
        return new ResponseEntity<>(new StandardResponse(200, "Driver Data List! ", driverService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getDriverNewId() {
        String lastId = driverService.getLastId();
        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "G")
        int number = Integer.parseInt(lastId.substring(1)); // Extract the number (e.g., 0003)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding

        return new ResponseEntity<>(new StandardResponse(200, "New Driver code! ", newId), HttpStatus.OK);
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

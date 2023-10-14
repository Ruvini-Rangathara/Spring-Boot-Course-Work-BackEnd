package com.next.travel.vehicle_service.controller;


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
@RequestMapping("/api/v1/vehicle")
public class VehicleApi {

    private final VehicleService vehicleService;
    private final DriverService driverService;

    @Autowired
    public VehicleApi(VehicleService vehicleService, DriverService driverService) {
        this.vehicleService = vehicleService;
        this.driverService = driverService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addVehicle(@RequestBody VehicleDto vehicleDto){
        validateVehicleData(vehicleDto);
        vehicleService.save(vehicleDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Vehicle was saved!",vehicleDto.toString()),
                HttpStatus.CREATED
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findVehicle(@PathVariable String id){
        VehicleDto vehicleDto = vehicleService.searchById(id);
        return new ResponseEntity<>(
                new StandardResponse(200,"Vehicle was found!",vehicleService.searchById(id)),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateVehicle(@RequestParam String id, @RequestBody VehicleDto vehicleDto){
        validateVehicleData(vehicleDto);
        vehicleService.update(vehicleDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Updated Vehicle Data!", vehicleDto.toString()),
                HttpStatus.OK
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteVehicle(@PathVariable String id){
        vehicleService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Deleted Vehicle Data!", id),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllVehicle(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Vehicle Data List! ", vehicleService.getAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/vehicleid")
    public ResponseEntity<StandardResponse> getVehicleNewId(){
        String lastId = vehicleService.getLastId(); // Get the last ID
        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "G")
        int number = Integer.parseInt(lastId.substring(1)); // Extract the number (e.g., 0003)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding


        return new ResponseEntity<>(
                new StandardResponse(200,"New Vehicle code! ", newId),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/driverid")
    public ResponseEntity<StandardResponse> getDriverNewId(){
        String lastId = driverService.getLastId();
        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "G")
        int number = Integer.parseInt(lastId.substring(1)); // Extract the number (e.g., 0003)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding

        return new ResponseEntity<>(
                new StandardResponse(200,"New Driver code! ", newId),
                HttpStatus.OK
        );
    }


    private void validateVehicleData(VehicleDto vehicleDto) throws RuntimeException {
        if (!Pattern.compile("^V\\d{3,}$").matcher(vehicleDto.getVehicleId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^([a-zA-Z]+( [a-zA-Z]+)*)$").matcher(vehicleDto.getDriverDto().getName()).matches()) {
            throw new InvalidException("Invalid name type!");

        } else if (!(Pattern.compile("^D\\d{3,}$").matcher(String.valueOf(vehicleDto.getDriverDto().getDriverId())).matches())) {
            throw new InvalidException("invalid id");

        } else if (!Pattern.compile("^[1-9]\\\\d*$").matcher(String.valueOf(vehicleDto.getSeatCapacity())).matches()) {
            throw new InvalidException("Invalid capacity!");

        } else if (!Pattern.compile("^\\d{10}$").matcher(vehicleDto.getDriverDto().getContactNo()).matches()) {
            throw new InvalidException("Invalid contact number!");
        }
    }

}

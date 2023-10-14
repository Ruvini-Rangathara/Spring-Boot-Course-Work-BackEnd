package com.next.travel.vehicle_service.controller;


import com.next.travel.vehicle_service.dto.VehicleDto;
import com.next.travel.vehicle_service.exception.InvalidException;
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

    @Autowired
    public VehicleApi(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
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

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getLastId(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Last Vehicle code! ", vehicleService.getLastId()),
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

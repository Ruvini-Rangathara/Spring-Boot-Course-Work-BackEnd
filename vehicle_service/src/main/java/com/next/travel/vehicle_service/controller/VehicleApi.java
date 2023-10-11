package com.next.travel.vehicle_service.controller;


import com.next.travel.vehicle_service.dto.VehicleDto;
import com.next.travel.vehicle_service.service.VehicleService;
import com.next.travel.vehicle_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}

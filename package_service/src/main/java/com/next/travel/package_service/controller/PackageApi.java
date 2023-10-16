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
@CrossOrigin("*")
public class PackageApi {

    @Value("${guide-service-endpoint}")
    private String guideDataEndpoint;

    @Value("${hotel-service-endpoint}")
    private String hotelDataEndpoint;

    @Value("${vehicle-service-endpoint}")
    private String vehicleDataEndpoint;

    @Value("${user-service-endpoint}")
    private String userDataEndpoint;

    @Value("${room-service-endpoint}")
    private String roomDataEndpoint;

    private final PackageService packageService;

    @Autowired
    public PackageApi(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    public ResponseEntity<StandardResponse> addPackage(@RequestBody PackageDto packageDto) {
        validatePackageData(packageDto);
        packageService.save(packageDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", packageDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findPackage(@PathVariable String id) {
        return new ResponseEntity<>(new StandardResponse(200, "Package was found!", packageService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updatePackage(@RequestParam String id, @RequestBody PackageDto packageDto) {
        validatePackageData(packageDto);
        packageService.update(packageDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Package Data!", packageDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deletePackage(@PathVariable String id) {
        packageService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Package Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllPackage() {
        return new ResponseEntity<>(new StandardResponse(200, "Package Data List! ", packageService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {
        String lastId = packageService.getLastId();
        String prefix = lastId.substring(0, 1);
        int number = Integer.parseInt(lastId.substring(1));
        number++;
        String newId = String.format("%s%04d", prefix, number);

        return new ResponseEntity<>(new StandardResponse(200, "new Package id! ", newId), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    GuideDto getFullProfileGuide(@RequestParam String guideId){

        //Method -1 - with RestTemplate(legacy)
//        RestTemplate restTemplate = new RestTemplate();
//        CustomerOrder initialCustomer = restTemplate.getForObject(customerDataEndpoint + customerId, CustomerOrder.class);
//        return orderService.getFullProfileData(initialCustomer);

        //Method -2 - with WebClient(modern)
        WebClient webClient = WebClient.create(guideDataEndpoint + guideId);
        Mono<GuideDto> responseGuide  = webClient.get()
                .retrieve() // fetch the data
                .bodyToMono(GuideDto.class);
        return packageService.getFullProfileDataOfGuide(responseGuide.block());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    VehicleDto getFullProfileVehicle(@RequestParam String vehicleId){
        WebClient webClient = WebClient.create(vehicleDataEndpoint + vehicleId);
        Mono<VehicleDto> responseVehicle  = webClient.get()
                .retrieve() // fetch the data
                .bodyToMono(VehicleDto.class);
        return packageService.getFullProfileDataOfVehicle(responseVehicle.block());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoomDto> getFullProfileRooms(@RequestParam List<String> roomIdList){
        List<RoomDto> list = new ArrayList<>();
        for (String id : roomIdList) {
            WebClient webClient = WebClient.create(roomDataEndpoint + id);
            Mono<RoomDto> responseRoom  = webClient.get()
                    .retrieve() // fetch the data
                    .bodyToMono(RoomDto.class);
            list.add(packageService.getFullProfileDataOfRoom(responseRoom.block()));
        }
        return list;
    }


    private void validatePackageData(PackageDto packageDto) throws RuntimeException {
        if (!Pattern.compile("^P\\d{3,}$").matcher(packageDto.getPackageId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(packageDto.getPackageValue())).matches()) {
            throw new InvalidException("Invalid package value!");

        }else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(packageDto.getPaidValue())).matches()) {
                throw new InvalidException("Invalid paid value!");

        }else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfNights())).matches()) {
            throw new InvalidException("Invalid No Of Nights!");

        }else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfDays())).matches()) {
            throw new InvalidException("Invalid No Of Days!");
        }
        else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getHeadCount())).matches()) {
            throw new InvalidException("Invalid head count!");
        }
        else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfChildren())).matches()) {
            throw new InvalidException("Invalid no Of Children!");
        }
        else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(packageDto.getNoOfAdults())).matches()) {
            throw new InvalidException("Invalid No Of Adults!");
        }

    }


}

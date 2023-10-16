package com.next.travel.package_service.controller;

import com.next.travel.package_service.dto.*;
import com.next.travel.package_service.exception.InvalidException;
import com.next.travel.package_service.service.InsuranceService;
import com.next.travel.package_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/insurance")
@CrossOrigin("*")
public class InsuranceApi {

    private final InsuranceService insuranceService;

    @Autowired
    public InsuranceApi(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addInsurance(@RequestBody InsuranceDto insuranceDto) {
        validateInsuranceData(insuranceDto);
        insuranceService.save(insuranceDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", insuranceDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findInsurance(@PathVariable String id) {
        return new ResponseEntity<>(new StandardResponse(200, "Insurance was found!", insuranceService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateInsurance(@RequestParam String id, @RequestBody InsuranceDto insuranceDto) {
        validateInsuranceData(insuranceDto);
        insuranceService.update(insuranceDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Insurance Data!", insuranceDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteInsurance(@PathVariable String id) {
        insuranceService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Insurance Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllInsurance() {
        return new ResponseEntity<>(new StandardResponse(200, "Insurance Data List! ", insuranceService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {
        String lastId = insuranceService.getLastId();
        String prefix = lastId.substring(0, 1);
        int number = Integer.parseInt(lastId.substring(1));
        number++;
        String newId = String.format("%s%04d", prefix, number);

        return new ResponseEntity<>(new StandardResponse(200, "new Insurance id! ", newId), HttpStatus.OK);
    }


    private void validateInsuranceData(InsuranceDto insuranceDto) throws RuntimeException {
        if (!Pattern.compile("^I\\d{3,}$").matcher(insuranceDto.getId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(insuranceDto.getPerOnePerson())).matches()) {
            throw new InvalidException("Invalid value!");

        }else if (!Pattern.compile("^\\d+\\.\\d{2}$\n").matcher(String.valueOf(insuranceDto.getLowerRange())).matches()) {
            throw new InvalidException("Invalid lower Range!");

        }else if (!Pattern.compile("^\\d+$").matcher(String.valueOf(insuranceDto.getUpperRange())).matches()) {
            throw new InvalidException("Invalid upper Range!");

        }
    }

}

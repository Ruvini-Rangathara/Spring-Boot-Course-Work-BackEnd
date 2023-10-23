package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.OptionDto;
import com.next.travel.hotel_service.exception.InvalidException;
import com.next.travel.hotel_service.service.OptionService;
import com.next.travel.hotel_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/v1/option")
public class OptionApi {

    private final OptionService optionService;

    @Autowired
    public OptionApi(OptionService optionService) {
        this.optionService = optionService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addOption(@RequestBody OptionDto optionDto) {
        validateOptionData(optionDto);
        optionService.save(optionDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", optionDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findOption(@PathVariable String id) {
        return new ResponseEntity<>(new StandardResponse(200, "Option was found!", optionService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateOption(@RequestParam String id, @RequestBody OptionDto optionDto) {
        validateOptionData(optionDto);
        optionService.update(optionDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Option Data!", optionDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteOption(@PathVariable String id) {
        optionService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Option Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllOption() {
        return new ResponseEntity<>(new StandardResponse(200, "Option Data List! ", optionService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {
        String lastId = optionService.getLastId(); // Get the last ID
        String prefix = lastId.substring(0, 3); // Extract the prefix (e.g., "OPT")
        int number = Integer.parseInt(lastId.substring(3)); // Extract the number (e.g., 0001)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding

        return new ResponseEntity<>(new StandardResponse(200, "New Option id! ", newId), HttpStatus.OK);
    }

    private void validateOptionData(OptionDto optionDto) throws RuntimeException {
        if (!Pattern.compile("OPT\\\\d{4}").matcher(optionDto.getOptionId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^[1-9]\\\\d*$").matcher(String.valueOf(optionDto.getOptionNumber())).matches()) {
            throw new InvalidException("Invalid capacity!");

        } else if (!(Pattern.compile("^\\\\d+(\\\\.\\\\d{2})?$").matcher(String.valueOf(optionDto.getPrice())).matches() && optionDto.getPrice() > 0)) {
            throw new InvalidException("invalid price");
        }
    }
}

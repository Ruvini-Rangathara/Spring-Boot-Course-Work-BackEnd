package com.next.travel.guide_service.controller;

import com.next.travel.guide_service.dto.GuideDto;
import com.next.travel.guide_service.exception.InvalidException;
import com.next.travel.guide_service.service.GuideService;
import com.next.travel.guide_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/guide")
public class GuideApi {

    private final GuideService guideService;

    @Autowired
    public GuideApi(GuideService guideService) {
        this.guideService = guideService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addGuide(@RequestBody GuideDto guideDto) {
        validateGuideData(guideDto);
        guideService.save(guideDto);
        return new ResponseEntity<>(new StandardResponse(201, "Guide was saved!", guideDto.toString()), HttpStatus.CREATED);

        //http://localhost:8000/api/v1/doctors -> POST

        // Body -> raw
        // {
        //    "name": "ruvini",
        //    "address": "panadura",
        //    "contact":"0786628489",
        //    "salary":60000
        //}
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findGuide(@PathVariable String id) {
        GuideDto guideDto = guideService.searchById(id);
        return new ResponseEntity<>(new StandardResponse(200, "Guide was found!", guideService.searchById(id)), HttpStatus.OK);

        //http://localhost:8000/api/v1/doctors/D0001            -> GET
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateGuide(@RequestParam String id, @RequestBody GuideDto guideDto) {
        validateGuideData(guideDto);
        guideService.update(guideDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Guide Data!", guideDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteGuide(@PathVariable String id) {
        guideService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Guide Data!", id), HttpStatus.NO_CONTENT);

        //http://localhost:8000/api/v1/doctors/D0001            -> DELETE
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllGuide() {
        return new ResponseEntity<>(new StandardResponse(200, "Guide Data List! ", guideService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {

        String lastId = guideService.getLastId(); // Get the last ID
        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "G")
        int number = Integer.parseInt(lastId.substring(1)); // Extract the number (e.g., 0003)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding

        return new ResponseEntity<>(new StandardResponse(200, "New Guide id! ", newId), HttpStatus.OK);
    }

    private void validateGuideData(GuideDto guideDto) throws RuntimeException {
        if (!Pattern.compile("^G\\d{3,}$").matcher(guideDto.getGuideId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^([a-zA-Z]+( [a-zA-Z]+)*)$").matcher(guideDto.getName()).matches()) {
            throw new InvalidException("Invalid name type!");

        } else if (!(Pattern.compile("^\\d+$").matcher(String.valueOf(guideDto.getAge())).matches() && guideDto.getAge() > 0 && guideDto.getAge() <100)) {
            throw new InvalidException("invalid age");

        } else if (!(guideDto.getGender().equalsIgnoreCase("male") || guideDto.getGender().equalsIgnoreCase("female"))) {
            throw new InvalidException("invalid gender");

        } else if (!Pattern.compile("^\\d{10}$").matcher(guideDto.getContactNo()).matches()) {
            throw new InvalidException("Invalid contact number!");

        } else if (!Pattern.compile("^(Rs\\s)?\\d+(\\.\\d{1,2})?$").matcher(String.valueOf(guideDto.getManDayValue())).matches()) {
            throw new InvalidException("Invalid price in Sri Lankan Rupees!");
        }
    }

}

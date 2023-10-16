package com.next.travel.user_service.controller;


import com.next.travel.user_service.dto.PromotionDto;
import com.next.travel.user_service.exception.InvalidException;
import com.next.travel.user_service.service.PromotionService;
import com.next.travel.user_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/promotion")
public class PromotionApi {


    private final PromotionService promotionService;

    @Autowired
    public PromotionApi(PromotionService promotionService) {
        this.promotionService = promotionService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addPromotion(@RequestBody PromotionDto promotionDto) {
        validatePromotionData(promotionDto);
        promotionService.save(promotionDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", promotionDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findPromotion(@PathVariable String id) {
        PromotionDto promotionDto = promotionService.searchById(id);
        return new ResponseEntity<>(new StandardResponse(200, "Promotion was found!", promotionService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updatePromotion(@RequestParam String id, @RequestBody PromotionDto promotionDto) {
        validatePromotionData(promotionDto);
        promotionService.update(promotionDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Promotion Data!", promotionDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deletePromotion(@PathVariable String id) {
        promotionService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Promotion Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllPromotion() {
        return new ResponseEntity<>(new StandardResponse(200, "Promotion Data List! ", promotionService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {
        String lastId = promotionService.getLastId(); // Get the last ID
        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "G")
        int number = Integer.parseInt(lastId.substring(1)); // Extract the number (e.g., 0003)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding

        return new ResponseEntity<>(new StandardResponse(200, "New Promotion code! ", newId), HttpStatus.OK);
    }

    private void validatePromotionData(PromotionDto promotionDto) throws RuntimeException {
        if (!Pattern.compile("^P\\d{3,}$").matcher(promotionDto.getPromotionId()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^\\d+\\.\\d{2}$").matcher(String.valueOf(promotionDto.getLowerRange())).matches()) {
            throw new InvalidException("Invalid range type!");
        }
        else if (!Pattern.compile("^\\d+\\.\\d{2}$").matcher(String.valueOf(promotionDto.getUpperRange())).matches()) {
            throw new InvalidException("Invalid range type!");
        }
        else if (!Pattern.compile("^\\d+\\.\\d$").matcher(String.valueOf(promotionDto.getRate())).matches()) {
            throw new InvalidException("Invalid rate type!");
        }
    }


}

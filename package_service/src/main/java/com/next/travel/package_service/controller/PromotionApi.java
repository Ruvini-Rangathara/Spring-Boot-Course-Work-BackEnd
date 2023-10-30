package com.next.travel.package_service.controller;


import com.next.travel.package_service.dto.PromotionDto;
import com.next.travel.package_service.exception.InvalidException;
import com.next.travel.package_service.service.PromotionService;
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
    public ResponseEntity<?> addPromotion(@RequestBody PromotionDto promotionDto) {
        validatePromotionData(promotionDto);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPromotion(@PathVariable String id) {
        PromotionDto promotionDto = promotionService.searchById(id);
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updatePromotion(@RequestParam String id, @RequestBody PromotionDto promotionDto) {
        validatePromotionData(promotionDto);
        promotionService.update(promotionDto);
        return null;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable String id) {
        promotionService.delete(id);
        return null;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> findAllPromotion() {
        return null;
    }

    @GetMapping(path = "/id")
    public ResponseEntity<?> getNewId() {
        String lastId = promotionService.getLastId(); // Get the last ID
        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "G")
        int number = Integer.parseInt(lastId.substring(1)); // Extract the number (e.g., 0003)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding

        return null;
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

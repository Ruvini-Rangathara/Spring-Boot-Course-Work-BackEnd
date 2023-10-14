package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.DiscountDto;
import com.next.travel.hotel_service.exception.InvalidException;
import com.next.travel.hotel_service.service.DiscountService;
import com.next.travel.hotel_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountApi {
    private final DiscountService discountService;

    @Autowired
    public DiscountApi(DiscountService discountService) {
        this.discountService = discountService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addDiscount(@RequestBody DiscountDto discountDto) {
        validateDiscountData(discountDto);
        discountService.save(discountDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", discountDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findDiscount(@PathVariable String id) {
        return new ResponseEntity<>(new StandardResponse(200, "Discount was found!", discountService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateDiscount(@RequestParam String id, @RequestBody DiscountDto discountDto) {
        validateDiscountData(discountDto);
        discountService.update(discountDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Discount Data!", discountDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteDiscount(@PathVariable String id) {
        discountService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Discount Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllDiscount() {
        return new ResponseEntity<>(new StandardResponse(200, "Discount Data List! ", discountService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {
        String lastId = discountService.getLastId();
        int number = Integer.parseInt(lastId.substring(3));
        number++;
        String newId = String.format("DIS%04d", number);

        return new ResponseEntity<>(new StandardResponse(200, "new Discount id! ", newId), HttpStatus.OK);
    }

    private void validateDiscountData(DiscountDto discountDto) throws RuntimeException {
        if (!Pattern.compile("^DIS\\\\d{4}$").matcher(discountDto.getCode()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^\\d+(\\.\\d+)?$\n").matcher(String.valueOf(discountDto.getRate())).matches()) {
            throw new InvalidException("Invalid rate!");
        }
    }

}

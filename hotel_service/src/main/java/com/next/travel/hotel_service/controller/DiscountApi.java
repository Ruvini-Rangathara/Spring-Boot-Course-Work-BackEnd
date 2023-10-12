package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.DiscountDto;
import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.service.DiscountService;
import com.next.travel.hotel_service.service.HotelService;
import com.next.travel.hotel_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hotel")
public class DiscountApi {
    private final DiscountService discountService;

    @Autowired
    public DiscountApi(DiscountService discountService) {
        this.discountService = discountService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addDiscount(@RequestBody DiscountDto discountDto) {
        discountService.save(discountDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", discountDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findDiscount(@PathVariable String id) {
        DiscountDto discountDto = discountService.searchById(id);
        return new ResponseEntity<>(new StandardResponse(200, "Discount was found!", discountService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateDiscount(@RequestParam String id, @RequestBody DiscountDto discountDto) {
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
    public ResponseEntity<StandardResponse> getLastId(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Last Discount id! ", discountService.getLastId()),
                HttpStatus.OK
        );
    }

}

package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.dto.OptionDto;
import com.next.travel.hotel_service.exception.InvalidException;
import com.next.travel.hotel_service.service.HotelService;
import com.next.travel.hotel_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/hotel")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HotelApi {

    private final HotelService hotelService;

    @Autowired
    public HotelApi(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/get/lastId")
    public String getOngoingHotelId() {
        System.out.println("getOngoingHotelId() called");
        String ongoingHotelId = hotelService.getLastId();
        System.out.println("ongoingHotelId = " + ongoingHotelId);
        return ongoingHotelId;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveHotel(@RequestPart("img1") byte[] img1,
                                       @RequestPart("img2") byte[] img2,
                                       @RequestPart("img3") byte[] img3,
                                       @RequestPart("img4") byte[] img4,
                                       @RequestPart("hotel") HotelDto hotelDto) {
        hotelDto.getImageList().add(img1);
        hotelDto.getImageList().add(img2);
        hotelDto.getImageList().add(img3);
        hotelDto.getImageList().add(img4);
        try {
            validateHotelDetails(hotelDto);
            if (hotelService.existById(hotelDto.getHotelCode())) {
                return ResponseEntity.badRequest().body("Hotel is already exists");
            }
            System.out.println("Api -> hotelDto = " + hotelDto);
            HotelDto save = hotelService.save(hotelDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private void validateHotelDetails(HotelDto hotelDto) {
        if (!Pattern.compile("^H\\d{3,}$").matcher(hotelDto.getHotelCode()).matches())
            throw new RuntimeException("Invalid hotel id");
        if (!Pattern.compile("^[A-Za-z0-9 ]{3,}$").matcher(hotelDto.getName()).matches())
            throw new RuntimeException("Invalid hotel name");
        if (!Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(hotelDto.getEmail()).matches())
            throw new RuntimeException("Invalid hotel email");

        if (!Pattern.compile("^[A-Za-z0-9 ]{3,}$").matcher(hotelDto.getLocation()).matches())
            throw new RuntimeException("Invalid hotel location");
        try {
            if (!Pattern.compile("^\\d{1,}$").matcher(String.valueOf(hotelDto.getStartRate())).matches()) {
                throw new RuntimeException("Invalid hotel star rate");
            } else {
                int star_rate = Integer.parseInt(String.valueOf(hotelDto.getStartRate()));
                if (star_rate < 1 || star_rate > 5) {
                    throw new RuntimeException("Invalid hotel star rate");
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid hotel star rate");
        }

//        if (!(Pattern.compile("true").matcher(hotelDto.getPetsAllowedOrNot()).matches() || Pattern.compile("false").matcher(hotelDto.getPetsAllowedOrNot()).matches()))
//            throw new RuntimeException("Invalid hotel pet allowed");   // do nothing

//        try {
//            if (!Pattern.compile("^\\d+(?:\\.\\d+)?$").matcher(String.valueOf(hotelDto.getTax())).matches())
//                throw new RuntimeException("Invalid fee per day!");
//        } catch (NumberFormatException e) {
//            throw new RuntimeException("Invalid tax !");
//        }

        hotelDto.getOptionsList().forEach(element -> {
            if (element.getOptionNumber() == 0 && element.getPrice() == 0) {
                throw new RuntimeException("Invalid option type !");
            }
        });

        hotelDto.getImageList().forEach(element -> {
            if (element == null || element.length == 0)
                throw new RuntimeException("Invalid or empty image found in the list.");
        });
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteHotel(@RequestHeader String id) {
        if (hotelService.existById(id)) {
            hotelService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Hotel not found");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getHotel(@RequestHeader String id) {
        if (hotelService.existById(id)) {
            HotelDto hotelDto = hotelService.searchById(id);
            return ResponseEntity.ok().body(hotelDto);
        } else {
            return ResponseEntity.badRequest().body("Hotel not found");
        }
    }

    @PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateHotel(@RequestPart("img1") byte[] img1,
                                         @RequestPart("img2") byte[] img2,
                                         @RequestPart("img3") byte[] img3,
                                         @RequestPart("img4") byte[] img4,
                                         @RequestPart("hotel") HotelDto hotelDto) {
        hotelDto.getImageList().add(img1);
        hotelDto.getImageList().add(img2);
        hotelDto.getImageList().add(img3);
        hotelDto.getImageList().add(img4);

        try {
            validateHotelDetails(hotelDto);
            if (!hotelService.existById(hotelDto.getHotelCode())) {
                return ResponseEntity.badRequest().body("Hotel not found!");
            }
            System.out.println("Api -> hotelDto = " + hotelDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllHotels() {
        try {
            return ResponseEntity.ok().body(hotelService.getAll());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

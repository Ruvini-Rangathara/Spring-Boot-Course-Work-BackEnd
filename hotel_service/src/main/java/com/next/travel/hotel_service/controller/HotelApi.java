package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.dto.OptionDto;
import com.next.travel.hotel_service.dto.RequestHotelDto;
import com.next.travel.hotel_service.service.HotelService;
import com.next.travel.hotel_service.util.mapper.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/hotel")
@CrossOrigin(origins = "http://localhost:63342")
public class HotelApi {

    private final HotelService hotelService;

    @Autowired
    public HotelApi(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/get/lastId")
    public String getNewHotelId() {
        System.out.println("getOngoingHotelId() called");
        String ongoingHotelId = hotelService.getNewId();
        System.out.println("ongoingHotelId in api = " + ongoingHotelId);
        return ongoingHotelId;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveHotel(@RequestPart("hotel_img1") byte[] img1,
                                       @RequestPart("hotel_img2") byte[] img2,
                                       @RequestPart("hotel_img3") byte[] img3,
                                       @RequestPart("hotel_img4") byte[] img4,
                                       @RequestPart("requestHotel") RequestHotelDto requestHotelDto) {

        HotelDto hotelDto = getHotelDto(requestHotelDto);

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

    private HotelDto getHotelDto(RequestHotelDto requestHotelDto){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelCode(requestHotelDto.getHotelCode());
        hotelDto.setName(requestHotelDto.getName());
        hotelDto.setCategory(requestHotelDto.getCategory());
        hotelDto.setStarRate(requestHotelDto.getStarRate());
        hotelDto.setLocation(requestHotelDto.getLocation());
        hotelDto.setEmail(requestHotelDto.getEmail());
        hotelDto.setContactNo(requestHotelDto.getContactNo());
        hotelDto.setPetsAllowedOrNot(requestHotelDto.getPetsAllowedOrNot());
        hotelDto.setCancellationCriteria(requestHotelDto.getCancellationCriteria());
        hotelDto.setOptionDto1(new OptionDto(1, requestHotelDto.getOpt1_price()));
        hotelDto.setOptionDto2(new OptionDto(2, requestHotelDto.getOpt2_price()));
        hotelDto.setOptionDto3(new OptionDto(3, requestHotelDto.getOpt3_price()));
        hotelDto.setOptionDto4(new OptionDto(4, requestHotelDto.getOpt4_price()));

        System.out.println("opt 1 price : "+requestHotelDto.getOpt1_price());
        System.out.println("opt 2 price : "+requestHotelDto.getOpt2_price());
        System.out.println("opt 3 price : "+requestHotelDto.getOpt3_price());
        System.out.println("opt 4 price : "+requestHotelDto.getOpt4_price());
        System.out.println();

        return hotelDto;
    }


    private void validateHotelDetails(HotelDto hotelDto) {
        if (!Pattern.compile("^H\\d{3,}$").matcher(hotelDto.getHotelCode()).matches())
            throw new RuntimeException("Invalid hotel id");
        if (!Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(hotelDto.getEmail()).matches())
            throw new RuntimeException("Invalid hotel email");
        if (!Pattern.compile("^-?\\d+$").matcher(String.valueOf(hotelDto.getContactNo())).matches())
            throw new RuntimeException("Invalid hotel contact number");

//        try {
//            if (!Pattern.compile("[1-5]").matcher(String.valueOf(hotelDto.getStartRate())).matches()) {
//                throw new RuntimeException("Invalid hotel star rate");
//            } else {
//                int star_rate = Integer.parseInt(String.valueOf(hotelDto.getStartRate()));
//                if (star_rate < 1 || star_rate > 5) {
//                    throw new RuntimeException("Invalid hotel star rate");
//                }
//            }
//        } catch (NumberFormatException e) {
//            throw new RuntimeException("Invalid hotel star rate");
//        }


        if (hotelDto.getOptionDto1().getOptionNumber() == 0 && hotelDto.getOptionDto1().getPrice() == 0) {
            throw new RuntimeException("Invalid option 1 !");
        }
        if (hotelDto.getOptionDto2().getOptionNumber() == 0 && hotelDto.getOptionDto2().getPrice() == 0) {
            throw new RuntimeException("Invalid option 2 !");
        }
        if (hotelDto.getOptionDto3().getOptionNumber() == 0 && hotelDto.getOptionDto3().getPrice() == 0) {
            throw new RuntimeException("Invalid option 3 !");
        }
        if (hotelDto.getOptionDto4().getOptionNumber() == 0 && hotelDto.getOptionDto4().getPrice() == 0) {
            throw new RuntimeException("Invalid option 4 !");
        }

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
            RequestHotelDto requestHotelDto = getRequestHotelDto(hotelDto);

            return ResponseEntity.ok().body(requestHotelDto);
        } else {
            return ResponseEntity.badRequest().body("Hotel not found");
        }
    }


    private RequestHotelDto getRequestHotelDto(HotelDto hotelDto){

        RequestHotelDto requestHotelDto = new RequestHotelDto();

        requestHotelDto.setHotelCode(hotelDto.getHotelCode());
        requestHotelDto.setName(hotelDto.getName());
        requestHotelDto.setCategory(hotelDto.getCategory());
        requestHotelDto.setStarRate(hotelDto.getStarRate());
        requestHotelDto.setLocation(hotelDto.getLocation());
        requestHotelDto.setEmail(hotelDto.getEmail());
        requestHotelDto.setContactNo(hotelDto.getContactNo());
        requestHotelDto.setPetsAllowedOrNot(hotelDto.getPetsAllowedOrNot());
        requestHotelDto.setCancellationCriteria(hotelDto.getCancellationCriteria());

        requestHotelDto.setOpt1_price(hotelDto.getOptionDto1().getPrice());
        requestHotelDto.setOpt2_price(hotelDto.getOptionDto2().getPrice());
        requestHotelDto.setOpt3_price(hotelDto.getOptionDto3().getPrice());
        requestHotelDto.setOpt4_price(hotelDto.getOptionDto4().getPrice());

        return requestHotelDto;
    }



    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateHotel(@RequestPart("hotel_img1") byte[] img1,
                                         @RequestPart("hotel_img2") byte[] img2,
                                         @RequestPart("hotel_img3") byte[] img3,
                                         @RequestPart("hotel_img4") byte[] img4,
                                         @RequestPart("requestHotel") RequestHotelDto requestHotelDto) {

        HotelDto hotelDto = getHotelDto(requestHotelDto);

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
            hotelService.update(hotelDto);
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


    @GetMapping("/check/")
    public ResponseEntity<?> existsByHotelId(@RequestHeader String hotel_code) {
        boolean isExists = hotelService.existById(hotel_code);
        System.out.println("isExists = " + isExists);
        if (isExists) return ResponseEntity.ok(true);
        return ResponseEntity.ok().body(false);
    }

}

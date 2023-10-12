package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.dto.RoomDto;
import com.next.travel.hotel_service.service.HotelService;
import com.next.travel.hotel_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotel")
public class HotelApi {

    private final HotelService hotelService;

    @Autowired
    public HotelApi(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addHotel(@RequestBody HotelDto hotelDto){
        hotelService.save(hotelDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Saved successfully!!",hotelDto.toString()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findHotel(@PathVariable String id){
        HotelDto hotelDto = hotelService.searchById(id);
        return new ResponseEntity<>(
                new StandardResponse(200,"Hotel was found!",hotelService.searchById(id)),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateHotel(@RequestParam String id, @RequestBody HotelDto hotelDto){
        hotelService.update(hotelDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Updated Hotel Data!", hotelDto.toString()),
                HttpStatus.OK
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteHotel (@PathVariable String id){
        hotelService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Deleted Hotel Data!", id),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllHotel(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Hotel Data List! ", hotelService.getAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/rooms")
    public ResponseEntity<StandardResponse> findRoomByHotelCodeAndCategory(@RequestParam String hotelCode, @RequestParam String category){
        List<RoomDto> roomsByHotelCodeAndCategory = hotelService.getRoomsByHotelCodeAndCategory(hotelCode, category);
        return new ResponseEntity<>(
                new StandardResponse(200,"Room Data List by hotel code and category! ", roomsByHotelCodeAndCategory),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getLastId(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Last Hotel code! ", hotelService.getLastId()),
                HttpStatus.OK
        );
    }

}

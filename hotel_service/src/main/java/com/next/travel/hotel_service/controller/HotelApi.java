package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.HotelDto;
import com.next.travel.hotel_service.dto.RoomDto;
import com.next.travel.hotel_service.exception.InvalidException;
import com.next.travel.hotel_service.service.HotelService;
import com.next.travel.hotel_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/hotel")
public class HotelApi {

    private final HotelService hotelService;

    @Autowired
    public HotelApi(HotelService hotelService) {
        this.hotelService = hotelService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addHotel(@RequestBody HotelDto hotelDto) {
        validateHotelData(hotelDto);
        hotelService.save(hotelDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", hotelDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findHotel(@PathVariable String id) {
        HotelDto hotelDto = hotelService.searchById(id);
        return new ResponseEntity<>(new StandardResponse(200, "Hotel was found!", hotelService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateHotel(@RequestParam String id, @RequestBody HotelDto hotelDto) {
        validateHotelData(hotelDto);
        hotelService.update(hotelDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Hotel Data!", hotelDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteHotel(@PathVariable String id) {
        hotelService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Hotel Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllHotel() {
        return new ResponseEntity<>(new StandardResponse(200, "Hotel Data List! ", hotelService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/rooms")
    public ResponseEntity<StandardResponse> findRoomByHotelCodeAndCategory(@RequestParam String hotelCode, @RequestParam String category) {
        List<RoomDto> roomsByHotelCodeAndCategory = hotelService.getRoomsByHotelCodeAndCategory(hotelCode, category);
        return new ResponseEntity<>(new StandardResponse(200, "Room Data List by hotel code and category! ", roomsByHotelCodeAndCategory), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getNewId() {
        String lastId = hotelService.getLastId(); // Get the last ID
        String prefix = lastId.substring(0, 1); // Extract the prefix (e.g., "G")
        int number = Integer.parseInt(lastId.substring(1)); // Extract the number (e.g., 0003)
        number++; // Increment the number
        String newId = String.format("%s%04d", prefix, number); // Create the new ID with zero-padding

        return new ResponseEntity<>(new StandardResponse(200, "New Hotel code! ", newId), HttpStatus.OK);
    }

    private void validateHotelData(HotelDto hotelDto) throws RuntimeException {
        if (!Pattern.compile("^H\\d{3,}$").matcher(hotelDto.getHotelCode()).matches()) {
            throw new InvalidException("Invalid id type!");

        } else if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n").matcher(hotelDto.getEmail()).matches()) {
            throw new InvalidException("Invalid email type!");
        }
        for (String contactNumber : hotelDto.getContactNo()) {
            if (!(Pattern.compile("^0\\d{9}$").matcher(contactNumber).matches())) {
                throw new InvalidException("Invalid Contact No");
            }
        }
    }


}

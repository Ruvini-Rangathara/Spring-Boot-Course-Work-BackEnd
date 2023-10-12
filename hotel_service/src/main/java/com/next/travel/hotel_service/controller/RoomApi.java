package com.next.travel.hotel_service.controller;

import com.next.travel.hotel_service.dto.RoomDto;
import com.next.travel.hotel_service.service.RoomService;
import com.next.travel.hotel_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/room")
public class RoomApi {

    private final RoomService roomService;

    @Autowired
    public RoomApi(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addRoom(@RequestBody RoomDto roomDto) {
        roomService.save(roomDto);
        return new ResponseEntity<>(new StandardResponse(201, "Saved successfully!!", roomDto.toString()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> findRoom(@PathVariable String id) {
        RoomDto roomDto = roomService.searchById(id);
        return new ResponseEntity<>(new StandardResponse(200, "Room was found!", roomService.searchById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateRoom(@RequestParam String id, @RequestBody RoomDto roomDto) {
        roomService.update(roomDto);
        return new ResponseEntity<>(new StandardResponse(201, "Updated Room Data!", roomDto.toString()), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteRoom(@PathVariable String id) {
        roomService.delete(id);
        return new ResponseEntity<>(new StandardResponse(204, "Deleted Room Data!", id), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllRoom() {
        return new ResponseEntity<>(new StandardResponse(200, "Room Data List! ", roomService.getAll()), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getLastId(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Last Room id! ", roomService.getLastId()),
                HttpStatus.OK
        );
    }

}

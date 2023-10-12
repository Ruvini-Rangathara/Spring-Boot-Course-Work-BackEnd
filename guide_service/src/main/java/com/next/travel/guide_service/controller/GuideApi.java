package com.next.travel.guide_service.controller;

import com.next.travel.guide_service.dto.GuideDto;
import com.next.travel.guide_service.service.GuideService;
import com.next.travel.guide_service.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guide")
public class GuideApi {

    private final GuideService guideService;

    @Autowired
    public GuideApi(GuideService guideService) {
        this.guideService = guideService;
    }


    @PostMapping
    public ResponseEntity<StandardResponse> addGuide(@RequestBody GuideDto guideDto){
        guideService.save(guideDto);
        return new ResponseEntity<>(
                new StandardResponse(201,"Guide was saved!",guideDto.toString()),
                HttpStatus.CREATED
        );

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
    public ResponseEntity<StandardResponse> findGuide(@PathVariable String id){
        GuideDto guideDto = guideService.searchById(id);
        return new ResponseEntity<>(
                new StandardResponse(200,"Guide was found!",guideService.searchById(id)),
                HttpStatus.OK
        );

        //http://localhost:8000/api/v1/doctors/D0001            -> GET
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateGuide(@RequestParam String id, @RequestBody GuideDto guideDto){
        guideService.update(guideDto);

        return new ResponseEntity<>(
                new StandardResponse(201,"Updated Guide Data!", guideDto.toString()),
                HttpStatus.OK
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteGuide(@PathVariable String id){
        guideService.delete(id);
        return new ResponseEntity<>(
                new StandardResponse(204,"Deleted Guide Data!", id),
                HttpStatus.NO_CONTENT
        );

        //http://localhost:8000/api/v1/doctors/D0001            -> DELETE
    }

    @GetMapping(path = "/list")
    public ResponseEntity<StandardResponse> findAllGuide(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Guide Data List! ", guideService.getAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/id")
    public ResponseEntity<StandardResponse> getLastId(){
        return new ResponseEntity<>(
                new StandardResponse(200,"Last Guide id! ", guideService.getLastId()),
                HttpStatus.OK
        );
    }

}

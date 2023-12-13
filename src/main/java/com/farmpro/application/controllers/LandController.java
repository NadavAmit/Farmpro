package com.farmpro.application.controllers;

import com.farmpro.application.entities.Land;
import com.farmpro.application.services.LandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/land")
@RequiredArgsConstructor
public class LandController {

    private final LandService landService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Land>> getAllLands(){
         List<Land> retrievedLandList =  landService.getAllLands();
        return new ResponseEntity<List<Land>>(retrievedLandList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Land> getLandById(@PathVariable("id") Long id){
        Land retrievedLand = landService.getLandById(id);
        return new ResponseEntity<Land>(retrievedLand,HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Land> createLand(@RequestBody Land land){
        Land savedLand = landService.createLand(land);
        return new ResponseEntity<Land>(savedLand,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Land> updateLand(@PathVariable("id") Long id, @RequestBody Land land) {
        Land savedLand = landService.updateLand(id,land);
        return new ResponseEntity<Land>(savedLand,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLand(@PathVariable("id") Long id) {
        landService.deleteLand(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}

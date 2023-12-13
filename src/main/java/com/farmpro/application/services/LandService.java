package com.farmpro.application.services;

import com.farmpro.application.entities.Land;
import com.farmpro.application.repositories.LandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LandService {
    private final LandRepository landRepository;

    public List<Land> getAllLands() {
        return landRepository.findAll();
    }
    public Land getLandById(Long id) {
        return landRepository.findById(id).get();
    }

    public Land createLand(Land land){
       return landRepository.save(land);
    }
    public Land updateLand (Long id,Land updatedLand){

        if(landRepository.existsById(id)){
            updatedLand.setId(id);
            return landRepository.save(updatedLand);
        }
        else{
            throw new NoSuchElementException("Land object with ID " + id + " not found in the database");
        }
    }
    public void deleteLand(Long id){
        landRepository.deleteById(id);
    }
}

package com.farmpro.application.services;

import com.farmpro.application.entities.Earning;
import com.farmpro.application.entities.Expense;
import com.farmpro.application.repositories.EarningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EarningService {

    private final EarningRepository earningRepository;

    public List<Earning> getAllEarnings() {
        return earningRepository.findAll();
    }
    public Earning getEarningById(Long id) {
        return earningRepository.findById(id).get();
    }

    public Earning createEarning(Earning earning){
        return earningRepository.save(earning);
    }
    public Earning updateEarning (Long id,Earning updatedEarning){

        if(earningRepository.existsById(id)){
            updatedEarning.setId(id);
            return earningRepository.save(updatedEarning);
        }
        else{
            throw new NoSuchElementException("Earning object with ID " + id + " not found in the database");
        }
    }
    public void deleteEarning(Long id){
        earningRepository.deleteById(id);
    }

    public List<Earning> getEarningsByFieldId(Long fieldId) {
        return earningRepository.findByFieldId(fieldId);
    }
}

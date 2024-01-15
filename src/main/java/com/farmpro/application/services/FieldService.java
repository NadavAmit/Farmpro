package com.farmpro.application.services;

import com.farmpro.application.entities.Field;
import com.farmpro.application.repositories.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }
    public Field getFieldById(Long id) {
        return fieldRepository.findById(id).get();
    }

    public Field createField(Field field){
       return fieldRepository.save(field);
    }
    public Field updateField (Long id,Field updatedField){

        if(fieldRepository.existsById(id)){
            updatedField.setId(id);
            return fieldRepository.save(updatedField);
        }
        else{
            throw new NoSuchElementException("Field object with ID " + id + " not found in the database");
        }
    }
    public void deleteField(Long id){
        fieldRepository.deleteById(id);
    }
}

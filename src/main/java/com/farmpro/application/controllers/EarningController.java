package com.farmpro.application.controllers;


import com.farmpro.application.dto.ActivityDTO;
import com.farmpro.application.dto.EarningDTO;
import com.farmpro.application.dto.ExpenseDTO;
import com.farmpro.application.entities.Activity;
import com.farmpro.application.entities.Earning;
import com.farmpro.application.entities.Expense;
import com.farmpro.application.entities.Field;
import com.farmpro.application.services.ActivityService;
import com.farmpro.application.services.EarningService;
import com.farmpro.application.services.ExpenseService;
import com.farmpro.application.services.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/earning")
@RequiredArgsConstructor
public class EarningController {
    private final EarningService earningService;
    private final FieldService fieldService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<EarningDTO>> getAllEarnings(){
        List<Earning> retrievedEarningsList =  earningService.getAllEarnings();
        List<EarningDTO> earningDTOList = retrievedEarningsList.stream().map(this::mapToDTO).toList();
        return new ResponseEntity<List<EarningDTO>>(earningDTOList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EarningDTO> getEarningById(@PathVariable("id") Long id){
        Earning retrievedEarning = earningService.getEarningById(id);
        EarningDTO earningDTO = mapToDTO(retrievedEarning);
        return new ResponseEntity<EarningDTO>(earningDTO,HttpStatus.OK);
    }
    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<EarningDTO>> getActivitiesByField(@PathVariable Long fieldId) {
        List<Earning> earningsForField = earningService.getEarningsByFieldId(fieldId);
        List<EarningDTO> earningDTOList = earningsForField.stream()
                .map(this::mapToDTO)
                .toList();
        return new ResponseEntity<List<EarningDTO>>(earningDTOList, HttpStatus.OK);
    }
    @PostMapping({"", "/"})
    public ResponseEntity<EarningDTO> createExpense(@RequestBody EarningDTO earningDTO){
        Field field = fieldService.getFieldById(earningDTO.getFieldId());
        Earning earning = mapToEntity(earningDTO,field);
        Earning savedEarning = earningService.createEarning(earning);
        EarningDTO earningDTOResponse = mapToDTO(savedEarning);
        return new ResponseEntity<EarningDTO>(earningDTOResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EarningDTO> updateEarning(@PathVariable("id") Long id, @RequestBody Earning earning) {
        Earning savedEarning = earningService.updateEarning(id,earning);
        EarningDTO earningDTO = mapToDTO(savedEarning);
        return new ResponseEntity<EarningDTO>(earningDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEarning(@PathVariable("id") Long id) {
        earningService.deleteEarning(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    private EarningDTO mapToDTO(Earning earning) {
        EarningDTO earningDTO = new EarningDTO();
        earningDTO.setId(earning.getId());
        earningDTO.setDate(earning.getDate());
        earningDTO.setEarningType(earning.getEarningType());
        earningDTO.setAmount(earning.getAmount());
        earningDTO.setFieldId(earning.getField().getId());
        return earningDTO;
    }

    private Earning mapToEntity(EarningDTO earningDTO,Field field) {
        Earning earning = new Earning();
        earning.setDate(earningDTO.getDate());
        earning.setEarningType(earningDTO.getEarningType());
        earning.setAmount(earningDTO.getAmount());
        earning.setField(field);
        return earning;
    }
}

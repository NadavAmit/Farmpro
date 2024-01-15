package com.farmpro.application.controllers;


import com.farmpro.application.dto.ActivityDTO;
import com.farmpro.application.entities.Activity;
import com.farmpro.application.entities.Field;
import com.farmpro.application.services.ActivityService;
import com.farmpro.application.services.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final FieldService fieldService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<ActivityDTO>> getAllActivities(){
        List<Activity> retrievedActivitiesList =  activityService.getAllActivities();
        List<ActivityDTO> activityDTOList = retrievedActivitiesList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<List<ActivityDTO>>(activityDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long id){
        Activity retrievedActivity = activityService.getActivityById(id);
        ActivityDTO retrievedActivityDTO = mapToDTO(retrievedActivity);
        return new ResponseEntity<ActivityDTO>(retrievedActivityDTO,HttpStatus.OK);
    }


    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<ActivityDTO>> getActivitiesByField(@PathVariable Long fieldId) {
        List<Activity> activitiesForField = activityService.getActivitiesByFieldId(fieldId);
        List<ActivityDTO> activityDTOList = activitiesForField.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(activityDTOList, HttpStatus.OK);
    }


    @PostMapping({"", "/"})
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO){
        Field field = fieldService.getFieldById(activityDTO.getFieldId());
        Activity activity = mapToEntity(activityDTO,field);
        Activity savedActivity = activityService.createActivity(activity);
        ActivityDTO activityDTOResponse = mapToDTO(savedActivity);
        return new ResponseEntity<ActivityDTO>(activityDTOResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable("id") Long id, @RequestBody Activity Activity) {
        Activity savedActivity = activityService.updateActivity(id,Activity);
        ActivityDTO activityDTOResponse = mapToDTO(savedActivity);
        return new ResponseEntity<ActivityDTO>(activityDTOResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable("id") Long id) {
        activityService.deleteActivity(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    private ActivityDTO mapToDTO(Activity activity) {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setId(activity.getId());
        activityDTO.setDate(activity.getDate());
        activityDTO.setStatus(activity.getStatus());
        activityDTO.setCropType(activity.getCropType());
        activityDTO.setFieldId(activity.getField().getId());
        return activityDTO;
    }

    private Activity mapToEntity(ActivityDTO activityDTO,Field field) {
        Activity activity = new Activity();
        activity.setDate(activityDTO.getDate());
        activity.setStatus(activityDTO.getStatus());
        activity.setCropType(activityDTO.getCropType());
        activity.setDate(null);
        activity.setField(field);
        return activity;
    }
}

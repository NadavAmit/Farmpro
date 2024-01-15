package com.farmpro.application.services;

import com.farmpro.application.entities.Activity;
import com.farmpro.application.repositories.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).get();
    }

    public Activity createActivity(Activity Activity){
        return activityRepository.save(Activity);
    }
    public Activity updateActivity (Long id,Activity updatedActivity){

        if(activityRepository.existsById(id)){
            updatedActivity.setId(id);
            return activityRepository.save(updatedActivity);
        }
        else{
            throw new NoSuchElementException("Activity object with ID " + id + " not found in the database");
        }
    }
    public void deleteActivity(Long id){
        activityRepository.deleteById(id);
    }

    public List<Activity> getActivitiesByFieldId(Long fieldId) {
        return activityRepository.findByFieldId(fieldId);
    }
}

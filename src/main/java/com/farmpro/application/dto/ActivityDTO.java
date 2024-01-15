package com.farmpro.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityDTO {

    private long id;
    private String date;
    private String status;
    private int cropType;
    private int fieldSize;
    private long fieldId;
}

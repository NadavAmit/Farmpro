package com.farmpro.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EarningDTO {

    private long id;
    private long fieldId;
    private String earningType;
    private long amount;
    private Date date;
}

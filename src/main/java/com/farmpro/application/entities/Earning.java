package com.farmpro.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Earning {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long fieldId;
    private String earningType;
    private long amount;
    private Date date;

}

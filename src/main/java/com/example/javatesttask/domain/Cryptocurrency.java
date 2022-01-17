package com.example.javatesttask.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Cryptocurrency {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String currName1;
    private String currName2;
    private Double lastPrice;
    private Timestamp time;
}

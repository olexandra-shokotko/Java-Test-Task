package com.example.javatesttask.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    private String currName;
    @NotNull
    private Double lastPrice;
//    @NotNull
    private Timestamp time;

    public String getCurrName() {
        return currName;
    }

    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}

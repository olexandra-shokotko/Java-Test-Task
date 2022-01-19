package com.example.javatesttask.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "currency")
public class Currency {
    static public final String[] possibleCurrencyNames = {"BTC", "ETH", "XRP"};

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    private String currName;
    @NotNull
    private Double lastPrice;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Currency() {
    }

    public Currency(String currName, Double lastPrice, Date createdAt) {
        this.currName = currName;
        this.lastPrice = lastPrice;
        this.createdAt = createdAt;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currName='" + currName + '\'' +
                ", lastPrice=" + lastPrice +
                ", createdAt=" + createdAt +
                '}';
    }
}

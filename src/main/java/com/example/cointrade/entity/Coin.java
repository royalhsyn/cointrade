package com.example.cointrade.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "coins")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "coinname")
    private String coinName;

    @Column(name = "price")
    private Double price;

    @Column(name = "updatedtime")
    private LocalDateTime updatedTime;

    @Column(name = "responselocaltime")
    private LocalDateTime responseLocalTime;

    @OneToMany(mappedBy = "coin",cascade = CascadeType.ALL)
    private List<Transaction> transactions=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }

    public void setResponseLocalTime(LocalDateTime responseLocalTime) {
        this.responseLocalTime = responseLocalTime;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

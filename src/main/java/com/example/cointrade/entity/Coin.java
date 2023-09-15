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
}

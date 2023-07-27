package com.elnafatehh.security.binance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PriceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false,name = "symbol")
    private String symbol;
    @Column(name = "price",nullable = false)
    private Double price;
    private Date date;

    public PriceData(String symbol, Double price) {
        this.symbol = symbol;
        this.price = price;
    }

}

package com.elnafatehh.security.user.crypto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String url;
    @ManyToOne
    @JoinColumn(name = "cryptocurrency_id")
    private CryptoCurrency cryptoCurrency;

}

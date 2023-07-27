package com.elnafatehh.security.user.favorite;

import com.elnafatehh.security.user.crypto.CryptoCurrency;
import com.elnafatehh.security.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "cryptoId", referencedColumnName = "id")
    private CryptoCurrency cryptoCurrency;
}

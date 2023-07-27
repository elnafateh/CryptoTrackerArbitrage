package com.elnafatehh.security.user.crypto;

import com.elnafatehh.security.user.favorite.Favorite;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "cryptocurrency")
public class CryptoCurrency {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String uuid;
    @Column(nullable = false)
    private String symbol;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column
    private String color;
    @Column
    private String iconUrl;
    @Column
    private String websiteUrl;
   // private String supply;
    @OneToMany(mappedBy = "cryptoCurrency")
    private List <Favorite> favorite;
    @OneToMany(mappedBy = "cryptoCurrency", cascade = CascadeType.ALL)
    private List<Link> links;
}

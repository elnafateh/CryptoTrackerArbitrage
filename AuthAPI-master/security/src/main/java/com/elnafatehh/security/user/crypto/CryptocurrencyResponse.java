package com.elnafatehh.security.user.crypto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CryptocurrencyResponse {
    private String status;
    private CryptocurrencyData data;
}

package com.elnafatehh.security.api;

import com.elnafatehh.security.user.crypto.CryptoCurrency;
import com.elnafatehh.security.user.crypto.CryptocurrencyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class CoinRankingApiClient {
    private final String baseUrl;
    public CoinRankingApiClient(@Value("https://coinranking1.p.rapidapi.com") String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public CryptoCurrency getCryptocurrency(String cryptocurrencyId){
        String url = baseUrl + "/coin/" + cryptocurrencyId;
        try {
            HttpResponse<String> response = Unirest.
                    get(url)
                    .header("X-RapidAPI-Key", "d48a9dfaa8mshe376ee6971a8585p13f6b2jsneb26f428242f")
                    .header("X-RapidAPI-Host", "coinranking1.p.rapidapi.com")
//                    .asObject(CryptocurrencyResponse.class);
                    .asString();
            String responseBody= response.getBody();

            if (responseBody.contains("message")) {
                // Handle the error response
                // For example, you can throw an exception or return a specific value indicating the error.
                throw new RuntimeException("Error: " + responseBody);
            }

            ObjectMapper objectMapper=new ObjectMapper();
            CryptocurrencyResponse cryptocurrencyResponse =objectMapper.readValue(responseBody,CryptocurrencyResponse.class);
            return cryptocurrencyResponse.getData().getCoin();
        } catch (JsonMappingException | UnirestException e) {
            throw new RuntimeException(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }




}

package com.elnafatehh.security.api.unirest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UnirestService {
    public String getAllCoins() {
        try {
            HttpResponse<String> response = Unirest.get("https://coinranking1.p.rapidapi.com/coins?referenceCurrencyUuid" +
                            "=yhjMzLPhuIDl&timePeriod=24h&tiers%5B0%5D=1&orderB" +
                            "y=marketCap&orderDirection=desc&limit=50&offset=0")
                    .header("X-RapidAPI-Key", "d48a9dfaa8mshe376ee6971a8585p13f6b2jsneb26f428242f")
                    .header("X-RapidAPI-Host", "coinranking1.p.rapidapi.com")
                    .asString();
            String responseBody = response.getBody();
            return (responseBody);
        } catch (UnirestException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Cacheable(value = "searchCache")
    public String filterAll(String query) {
        try {
            HttpResponse<String> response = Unirest.get("https://coinranking1.p.rapidapi.com/search-suggestions?referenceCurrencyUuid=yhjMzLPhuIDl&query=" + query)
                    .header("X-RapidAPI-Key", "d48a9dfaa8mshe376ee6971a8585p13f6b2jsneb26f428242f")
                    .header("X-RapidAPI-Host", "coinranking1.p.rapidapi.com")
                    .asString();
            String responseBody = response.getBody();
            return responseBody;
        } catch (UnirestException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Cacheable(value = "coinDetails")
    public String getCoinDetails(@PathVariable("coinId") String coinId) {
        try {
            HttpResponse<String> response = Unirest.get("https://coinranking1.p.rapidapi.com/coin/{coinId}?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h")
                    .header("X-RapidAPI-Key", "d48a9dfaa8mshe376ee6971a8585p13f6b2jsneb26f428242f")
                    .header("X-RapidAPI-Host", "coinranking1.p.rapidapi.com")
                    .routeParam("coinId", "Qwsogvtv82FCd")
                    .asString();
            return response.getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getExchanges() {
        try {
            HttpResponse<String> response = Unirest.get("https://coinranking1.p.rapidapi.com/exchanges?referenceCurrencyUuid=yhjMzLPhuIDl&limit=50&offset=0&orderBy=24hVolume&orderDirection=desc")
                    .header("X-RapidAPI-Key", "d48a9dfaa8mshe376ee6971a8585p13f6b2jsneb26f428242f")
                    .header("X-RapidAPI-Host", "coinranking1.p.rapidapi.com")
                    .asString();
            return response.getBody();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.elnafatehh.security.binance;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
@Component
public class BinanceAPI {
    private HttpClient httpClient;
    private final String baseUrl= "https://api.binance.com";


    public BinanceAPI(@Value(("${binance.api.key}")) String apiKeys,@Value(("${binance.api.secret}")) String secretKeys ) {
        //create Httpclient obj
        httpClient= HttpClientBuilder.create().build();


        // Create a RequestConfig object to set other parameters such as timeout, etc.
        RequestConfig requestConfig=RequestConfig.custom()
                .setConnectTimeout(500).setSocketTimeout(5000).build();

        // Add the API key and the request config to the HttpClient object
        httpClient = HttpClients.custom().setDefaultHeaders(Collections
                .singletonList(new BasicHeader("X-MBX-APIKEY",apiKeys))).setDefaultRequestConfig(requestConfig).build();
    }
    // A method that gets the price of a symbol using the /api/v3/ticker/price endpoint
    public String getPrice(String symbol)throws IOException {
        // Create a HttpGet object with the endpoint and the symbol parameter
        HttpGet httpGet= new HttpGet(baseUrl + "/api/v3/ticker/price?symbol=" + symbol);
        // Execute the HttpGet object and get the HttpResponse object
        HttpResponse httpResponse= httpClient.execute(httpGet);
        // Parse the HttpResponse object and get the price data as a string
        String priceData= EntityUtils.toString(httpResponse.getEntity());

        return priceData;
    }

}

package com.elnafatehh.security.binance;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class BinanceController {
    private final BinanceAPI binanceAPI;
    private final BinanceServe binanceServe;
    @GetMapping("/price")
    public ResponseEntity<String> getPrice(@RequestParam String symbol){
        try {
            // Call the getPrice method of the BinanceService object and get the price data
            String priceData =binanceServe.getPrice(symbol);
            // Return the price data with a 200 OK status code
            return ResponseEntity.ok(priceData);
        } catch (IOException e) {
              return ResponseEntity.status(500).body(null);
//            throw new RuntimeException(e);
        }
    }
    // A method that handles GET requests to the /priceData endpoint with a symbol parameter
    @GetMapping("/priceData")
    public ResponseEntity<PriceData> getPriceData(@RequestParam String symbol) {
        try {
            // Call the getPriceData method of the BinanceService object and get the PriceData object
            PriceData priceDataObject = binanceServe.getPriceData(symbol);
            // Return the PriceData object with a 200 OK status code
            return ResponseEntity.ok(priceDataObject);
        } catch (IOException e) {
            // Return an error message with a 500 Internal Server Error status code
            return ResponseEntity.status(500).body(null);
        }
    }
//    @PostMapping("/savePriceData")
//    public void savePriceData(@RequestBody PriceData priceData){
//        binanceServe.savePriceData(priceData);
//    }
//    @GetMapping("/list/priceData/{symbol}")
//    public List<PriceData> getPriceDataBySymbol(@PathVariable String symbol){
//        return binanceServe.getPriceDataBySymbol(symbol);
//    }
//    public void close(){
//        binanceServe.close();
//    }
}

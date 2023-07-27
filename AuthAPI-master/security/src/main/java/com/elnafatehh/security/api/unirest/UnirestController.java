package com.elnafatehh.security.api.unirest;

import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UnirestController {
    private final UnirestService service;
    @GetMapping("/coins")
    public ResponseEntity<String> getAllCoins() throws UnirestException {

            return ResponseEntity.ok(service.getAllCoins());
    }
   @GetMapping("/search")
        public ResponseEntity<String> filterAll(@RequestParam String query){
        return ResponseEntity.ok(service.filterAll(query));

        }

   @GetMapping("/coin/{coinId}")
    public ResponseEntity<String> getCoinDetails(String coinId){
        return ResponseEntity.ok(service.getCoinDetails(coinId));
   }
   @GetMapping("/exchanges")
   public ResponseEntity<String> getExchanges(){
        return  ResponseEntity.ok(service.getExchanges());
   }
}

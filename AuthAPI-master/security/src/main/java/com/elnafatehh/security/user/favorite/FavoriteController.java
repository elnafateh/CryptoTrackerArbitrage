package com.elnafatehh.security.user.favorite;

import com.elnafatehh.security.exception.NotFoundException;
import com.elnafatehh.security.user.User;
import com.elnafatehh.security.user.crypto.CryptoCurrency;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class FavoriteController {
    private final FavoriteService service;
    @GetMapping("/favorite")
   public ResponseEntity <List<CryptoCurrency>> getFavoriteCryptocurrencies(@AuthenticationPrincipal User user){
        List<CryptoCurrency>  favoriteCryptocurrencies=service.getFavoriteCryptocurrencies(user);
        if (favoriteCryptocurrencies.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
       return ResponseEntity.ok(favoriteCryptocurrencies);
    }
    @PostMapping("/favorite/{cryptocurrencyId}")
    public ResponseEntity<String> addFavoriteCryptocurrency(
            @AuthenticationPrincipal User user, @PathVariable("cryptocurrencyId") String cryptocurrencyId){
        service.addFavoriteCryptocurrency(user,cryptocurrencyId);
        return ResponseEntity.ok("Cryptocurrency added to favorites");
    }
    @DeleteMapping("/favorite/{cryptocurrencyId}")
    public ResponseEntity<String> removeFavoriteCryptocurrency(@AuthenticationPrincipal User user,@PathVariable("cryptocurrencyId") String cryptocurrencyId) throws NotFoundException{
        service.removeFavoriteCryptocurrency(user,cryptocurrencyId);
        return ResponseEntity.ok("Cryptocurrency removed from favorites");
    }
}

package com.elnafatehh.security.user.favorite;

import com.elnafatehh.security.api.CoinRankingApiClient;
import com.elnafatehh.security.exception.NotFoundException;
import com.elnafatehh.security.user.User;
import com.elnafatehh.security.user.UserRepository;
import com.elnafatehh.security.user.crypto.CryptoCurrency;
import com.elnafatehh.security.user.crypto.CryptoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final CryptoRepository cryptoRepository;
    private final CoinRankingApiClient coinRankingApiClient;
    @Cacheable(value = "favorites",key = "#user.id")
    public List<CryptoCurrency> getFavoriteCryptocurrencies(User user) {
        List<Favorite> favorites=favoriteRepository.findByUser(user);
        return favorites.stream().map(Favorite::getCryptoCurrency).collect(Collectors.toList());
    }
    @CachePut(value = "favorites",key = "#user.id")
    public void addFavoriteCryptocurrency(User user, String cryptocurrencyId) {
        CryptoCurrency cryptocurrency= coinRankingApiClient.getCryptocurrency(cryptocurrencyId);
       CryptoCurrency cryptoCurrency= cryptoRepository.save(cryptocurrency);

        Favorite favorite= new Favorite();
        favorite.setUser(user);
        favorite.setCryptoCurrency(cryptoCurrency);
        favoriteRepository.save(favorite);


    }
    @CacheEvict(value = "favorites",key = "#user.id")
    public void removeFavoriteCryptocurrency(User user, String cryptocurrencyId){
        CryptoCurrency cryptoCurrency=cryptoRepository.findByUuid(cryptocurrencyId);
        Favorite favorite =favoriteRepository.findByUserAndCryptoCurrency(user,cryptoCurrency);
        if (favorite != null){
           favoriteRepository.delete(favorite);
//            cryptoRepository.save(cryptoCurrency);
//            cryptoRepository.delete(cryptoCurrency);
        }
        else {
            throw new NotFoundException("Favorite cryptocurrency not found");
        }
    }
}

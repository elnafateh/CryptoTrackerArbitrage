package com.elnafatehh.security.user.favorite;

import com.elnafatehh.security.user.crypto.CryptoCurrency;
import com.elnafatehh.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
        List<Favorite> findByUser(User user);
       Favorite findByUserAndCryptoCurrency(User user, CryptoCurrency cryptocurrency);
    void deleteByUserAndCryptoCurrency (User user, String cryptoCurrency);
}

package com.elnafatehh.security.user.crypto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CryptoRepository extends JpaRepository<CryptoCurrency,Long> {

        Optional<CryptoCurrency> findById(Long cryptocurrencyId);
        CryptoCurrency findBySymbol(String symbol);
        CryptoCurrency findByUuid(String cryptocurrencyId);
}

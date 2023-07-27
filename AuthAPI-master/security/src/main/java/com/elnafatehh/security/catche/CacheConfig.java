package com.elnafatehh.security.catche;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {

        @Bean
        @Override
        public CacheManager cacheManager() {
            // Create and configure a cache manager, such as the ConcurrentMapCacheManager
            ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
            cacheManager.setCacheNames(Arrays.asList("favorites", "searchCache","coinDetails")); // Specify the cache names
            return cacheManager;
        }
    }



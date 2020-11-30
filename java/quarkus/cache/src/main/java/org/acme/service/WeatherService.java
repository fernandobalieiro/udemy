package org.acme.service;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import org.acme.data.Weather;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class WeatherService {

    @CacheResult(cacheName = "weather_status")
    public Weather provideWeather(@CacheKey final String city) {
        final var status = getWeatherStatus();
        return new Weather(city, status);
    }

    @CacheInvalidate(cacheName = "weather_status")
    public void invalidate(final String city) {
    }

    @CacheInvalidateAll(cacheName = "weather_status")
    public void invalidateAll() {
    }

    public String getWeatherStatus() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextBoolean() ? "Sunny" : "Rainy";
    }
}

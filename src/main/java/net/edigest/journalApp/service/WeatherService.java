package net.edigest.journalApp.service;

import net.edigest.journalApp.api.response.WeatherResponse;

import net.edigest.journalApp.cache.AppCache;
import net.edigest.journalApp.constants.Placeholders;
import net.edigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {
    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;
    @Value("${weather.api.key}")
    private String apikey;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }
        else{
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

            HttpStatusCode statusCode = response.getStatusCode();
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city,body, 300l);
            }
            return body;
        }

    }
}

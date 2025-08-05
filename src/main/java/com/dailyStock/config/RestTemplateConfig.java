package com.dailyStock.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Spring Boot configura automáticamente el RestTemplateBuilder por ti.
        // Aquí podrías añadir configuraciones base si lo necesitaras.
        return builder.build();
    }
}
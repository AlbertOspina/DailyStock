package com.dailyStock.repository;

import com.dailyStock.model.StockDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class ApiStockRepository {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiToken;

    public static final String QUOTE_PATH = "quote";

    @Autowired
    public ApiStockRepository(RestTemplate restTemplate,
                              @Value("${finnhub.api.base-url}") String baseUrl,
                              @Value("${finnhub.api.token}") String apiToken) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiToken = apiToken;
    }

    public JsonNode getStockData(String ticker) {
        JsonNode response;
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(QUOTE_PATH)
                .queryParam("symbol", ticker)
                .queryParam("token",apiToken)
                .toUriString();
        log.info("Requesting URL: {}", url);
        response = restTemplate.getForObject(url, JsonNode.class);
        log.info("Response: {}", response);

        return response;
    }
}

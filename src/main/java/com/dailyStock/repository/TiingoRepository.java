package com.dailyStock.repository;

import com.dailyStock.model.StockDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
@Slf4j
public class TiingoRepository {
    public static final String URL_TIINGO = "https://api.tiingo.com/tiingo/daily/";
    public static final String STOCK_PATH = "/prices";
    public static final String TOKEN = "?token=fc9a0e23dde6d1fd1757a6c222057af4b6948d21";

    private final RestTemplate restTemplate;

    @Autowired
    public TiingoRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<StockDTO> getStockData(String tiker) {
        String url = URL_TIINGO+tiker+STOCK_PATH+TOKEN;
        log.info(url);
        StockDTO[] response = restTemplate.getForObject(url, StockDTO[].class);

        return Arrays.asList(Objects.requireNonNull(response));
    }
}

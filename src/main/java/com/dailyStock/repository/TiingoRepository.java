package com.dailyStock.repository;

import com.dailyStock.model.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class TiingoRepository {
    public static final String URL_TIINGO = "https://api.tiingo.com/tiingo/daily/";
    public static final String TOKEN = "?token=fc9a0e23dde6d1fd1757a6c222057af4b6948d21";

    private final RestTemplate restTemplate;

    @Autowired
    public TiingoRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<StockDTO> getStockData(String tiker) {
        String url = URL_TIINGO+tiker+"/price"+TOKEN;

        // Usamos getForObject para hacer la petición GET.
        // ¡Ojo al truco! Como la respuesta es un arreglo JSON, le pedimos a RestTemplate
        // que lo mapee a un ARREGLO de PriceDataDTO (PriceDataDTO[].class).
        StockDTO[] response = restTemplate.getForObject(url, StockDTO[].class);

        // Luego, convertimos el arreglo a una Lista para que sea más fácil de manejar.
        return Arrays.asList(Objects.requireNonNull(response));
    }
}

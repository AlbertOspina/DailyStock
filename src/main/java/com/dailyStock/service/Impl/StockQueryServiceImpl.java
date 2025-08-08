package com.dailyStock.service.Impl;


import com.dailyStock.repository.ApiStockRepository;
import com.dailyStock.service.StockQueryService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockQueryServiceImpl implements StockQueryService {

    @Autowired
    ApiStockRepository apiStockRepository;

    public double getStockPrice(String ticker){
        JsonNode jsonNode;
        jsonNode = apiStockRepository.getStockData(ticker);
        return jsonNode.path("c").asDouble();
    }
}

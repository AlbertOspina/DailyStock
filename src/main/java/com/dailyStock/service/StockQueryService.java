package com.dailyStock.service;

import com.dailyStock.model.StockDTO;
import com.dailyStock.repository.TiingoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockQueryService {

    @Autowired
    TiingoRepository tiingoRepository;

    public StockDTO getStockData(String tiker){
        List<StockDTO> stockList = new ArrayList<>();
        stockList = tiingoRepository.getStockData(tiker);
        return stockList.get(0);
    }
}

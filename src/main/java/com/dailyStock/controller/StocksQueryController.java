package com.dailyStock.controller;

import com.dailyStock.service.Impl.StockExcelServiceImpl;
import com.dailyStock.service.Impl.StockQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StocksQueryController {

    @Autowired
    StockQueryServiceImpl stockQueryService;

    @Autowired
    StockExcelServiceImpl stockExcelService;

    @GetMapping()
    ResponseEntity<Map<String, Double>> getStock(@RequestParam(value = "ticker",required = true)String ticker) {
        Map<String, Double> response = new HashMap<>();
        double price = stockQueryService.getStockPrice(ticker);
        response.put("price",price);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/excel")
    void getStockExcel(){
        ArrayList<String> stockList = stockExcelService.getStockList();
    }

}

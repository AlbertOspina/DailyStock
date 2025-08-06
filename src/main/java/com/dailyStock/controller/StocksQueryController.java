package com.dailyStock.controller;

import com.dailyStock.model.StockDTO;
import com.dailyStock.service.StockQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StocksQueryController {

    @Autowired
    StockQueryService stockQueryService;

    @GetMapping()
    ResponseEntity<StockDTO> getStock(@RequestParam(value = "tiker",required = true)String tiker) {
        StockDTO stockDTO = new StockDTO();
        stockDTO = stockQueryService.getStockData(tiker);
        return new ResponseEntity<>(stockDTO, HttpStatus.OK);
    }
}

package com.dailyStock.controller;

import com.dailyStock.model.StockDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StocksQueryController {

    @GetMapping()
    StockDTO getStock(@RequestParam(value = "tiker",required = true)String tiker) {

    }
}

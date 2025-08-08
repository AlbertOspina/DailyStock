package com.dailyStock.service.Impl;

import com.dailyStock.service.StockExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@Slf4j
public class StockExcelServiceImpl implements StockExcelService {

    private final String excelFilePath;
    private final String sheetName;

    public StockExcelServiceImpl(@Value("${stock.excel.path}") String excelFilePath,
                                 @Value("${stock.excel.sheet}") String sheetName) {
        this.excelFilePath = excelFilePath;
        this.sheetName = sheetName;
    }

    @Override
    public void getStockList() {
        try {
            log.info(excelFilePath);
            FileInputStream file = new FileInputStream(new File(excelFilePath));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.getSheetAt(0);
            }

            Row row = sheet.getRow(1);
            if (row == null) {
                workbook.close();
                file.close();
                log.info("La fila especificada no existe.");
            }

            Cell cell = row.getCell(1);
            if (cell == null) {
                workbook.close();
                log.info("La celda especificada está vacía o no existe.");
            }

            log.info(cell.toString());
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}

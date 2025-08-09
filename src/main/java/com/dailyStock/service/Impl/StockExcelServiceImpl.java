package com.dailyStock.service.Impl;

import com.dailyStock.service.StockExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
@Slf4j
public class StockExcelServiceImpl implements StockExcelService {

    private final String excelFilename;
    private final String sheetName;
    private ResourceLoader resourceLoader;

    public StockExcelServiceImpl(@Value("${stock.excel.filename}") String excelFilename,
                                 @Value("${stock.excel.sheet}") String sheetName,
                                 ResourceLoader resourceLoader) {
        this.excelFilename = excelFilename;
        this.sheetName = sheetName;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ArrayList<String> getStockList() {
        log.info(excelFilename);
        Resource resource = resourceLoader.getResource("classpath:" + excelFilename);
        ArrayList<String> stockList = new ArrayList<>();

        try(InputStream inputStream = resource.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream)) {
            int i = 1;
            String stock = "No existe";
            do{
                stock = getCellValue(workbook, sheetName, i, 1);
                if (stock.equals("No existe"))
                    break;
                else
                    stockList.add(stock);
                log.info(stock);
                i++;
            }while(!stock.equals("No existe"));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return stockList;
    }

    public String getCellValue(Workbook workbook, String sheetName, int rowIndex, int columnIndex){
        log.info("Parametros recibidos: sheet {}, row {}, column {}", sheetName, rowIndex, columnIndex);
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.getSheetAt(0);
        }

        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            log.info("La fila especificada no existe.");
            return "No existe";
        }

        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            log.info("La celda especificada está vacía o no existe.");
            return "No existe";
        } else {
            return cell.getStringCellValue();
        }
    }
}

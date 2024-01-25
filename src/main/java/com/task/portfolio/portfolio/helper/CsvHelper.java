package com.task.portfolio.portfolio.helper;

import com.task.portfolio.portfolio.dao.StockDao;
import com.task.portfolio.portfolio.dto.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
@RequiredArgsConstructor

public class CsvHelper {

    private final StockDao stockDao;
    public static String TYPE = "text/csv";

    public boolean isCSV(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public void updateStocksFromCSV(MultipartFile file) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<StockDTO> stocks = null;

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setIsin(csvRecord.get("ISIN"));
                stockDTO.setName(csvRecord.get("SYMBOL"));
                stockDTO.setHigh(Double.parseDouble(csvRecord.get("HIGH")));
                stockDTO.setLow(Double.parseDouble(csvRecord.get("LOW")));
                stockDTO.setClose(Double.parseDouble(csvRecord.get("CLOSE")));
                stockDTO.setOpen(Double.parseDouble(csvRecord.get("OPEN")));

                stockDao.updateStock(stockDTO);
            }
        } catch (IOException e) {
            throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
        }
    }
}

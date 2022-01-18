package com.example.javatesttask.controller;

import com.example.javatesttask.domain.Currency;
import com.example.javatesttask.repository.CurrencyRepo;
import com.example.javatesttask.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cryptocurrencies")
public class MainController {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyRepo currencyRepo;

    @GetMapping("/minprice")
    public Currency getMinPrice(@RequestParam("name") String name) throws Exception {
        return currencyService.findMinByName(name);
    }

    @GetMapping("/maxprice")
    public Currency getMaxPrice(@RequestParam("name") String name) throws Exception {
        return currencyService.findMaxByName(name);
    }

    @GetMapping("")
    public List<Currency> getPage(@RequestParam("name") String name,
                        @PageableDefault(sort = {"lastPrice"}, direction = Sort.Direction.ASC) Pageable pageable) {

        return currencyRepo.findByCurrName(name, pageable).getContent();
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public String logicalException(Exception ex) {
        return new Error("error: " + ex.getMessage()).getMessage();
    }

    @GetMapping("/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=cryptocurrencies_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<String []> listCurr = currencyRepo.findMinMax();

        ICsvListWriter csvWriter = new CsvListWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Currency name", "Min price", "Max price"};

        csvWriter.writeHeader(csvHeader);

        for (String[] strings : listCurr) {
            csvWriter.write(strings);
        }

        csvWriter.close();
    }
}

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
//        return new ResponseEntity<Object>(new Object(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}

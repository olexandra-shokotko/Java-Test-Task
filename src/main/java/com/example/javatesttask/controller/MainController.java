package com.example.javatesttask.controller;

import com.example.javatesttask.domain.Currency;
import com.example.javatesttask.repository.CurrencyRepo;
import com.example.javatesttask.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cryptocurrencies")
public class MainController {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyRepo currencyRepo;

    @GetMapping("/")
    public List<Currency> getAll() {
        return currencyRepo.findAll();
    }

    @GetMapping("/minprice")
    public Currency getMinPrice(@RequestParam("name") String name) {
        return currencyService.findMinByName(name);
    }

    @GetMapping("/maxprice")
    public Currency getMaxPrice(@RequestParam("name") String name) {
        return currencyService.findMaxByName(name);
    }

    @GetMapping("?name={currency_name}&page={page_number}&size={page_size}")
    public void getPage(@PathVariable("currency_name") String name,
                        @PathVariable("page_number") String pageNumber,
                        @PathVariable("page_size") String pageSize) {

    }
}

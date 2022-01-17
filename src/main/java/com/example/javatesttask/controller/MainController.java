package com.example.javatesttask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/cryptocurrencies/minprice?name={currency_name}")
    public void getMinPrice(@PathVariable("currency_name") String name) {

    }

    @GetMapping("/cryptocurrencies/maxprice?name={currency_name}")
    public void getMaxPrice(@PathVariable("currency_name") String name) {

    }

    @GetMapping("/cryptocurrencies?name={currency_name}&page={page_number}&size={page_size}")
    public void getPage(@PathVariable("currency_name") String name,
                        @PathVariable("page_number") String pageNumber,
                        @PathVariable("page_size") String pageSize) {

    }
}

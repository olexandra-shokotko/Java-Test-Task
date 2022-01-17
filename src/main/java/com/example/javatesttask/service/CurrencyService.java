package com.example.javatesttask.service;

import com.example.javatesttask.domain.Currency;
import com.example.javatesttask.repository.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepo currencyRepo;

    public Currency findMinByName(String currName) {
        List<Currency> currencies = currencyRepo.findByCurrName(currName);

        double firstPrice = currencies.get(0).getLastPrice();
        Currency currencyToReturn = currencies.get(0);
        for (Currency currency : currencies) {
            if (currency.getLastPrice() < firstPrice) {
                firstPrice = currency.getLastPrice();
                currencyToReturn = currency;
            }
        }

        return currencyToReturn;
    }

    public Currency findMaxByName(String currName) {
        List<Currency> currencies = currencyRepo.findByCurrName(currName);

        double firstPrice = currencies.get(0).getLastPrice();
        Currency currencyToReturn = currencies.get(0);
        for (Currency currency : currencies) {
            if (currency.getLastPrice() > firstPrice) {
                firstPrice = currency.getLastPrice();
                currencyToReturn = currency;
            }
        }

        return currencyToReturn;
    }
}

package com.example.javatesttask.service;

import com.example.javatesttask.domain.Currency;
import com.example.javatesttask.domain.CurrencyDTO;
import com.example.javatesttask.repository.CurrencyRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepo currencyRepo;
    @Autowired
    CurrencyClient currencyClient;

    public Currency findMinByName(String currName) throws Exception {
        if (!Arrays.asList(Currency.possibleCurrencyNames).contains(currName)) {
            throw new Exception("invalid parameters (possible: BTC, ETH, XRP)");
        }

        List<Currency> currencies = getCurrenciesByCurrName(currName);

        Currency currencyToReturn;
        double firstPrice = currencies.get(0).getLastPrice();
        currencyToReturn = currencies.get(0);

        for (Currency currency : currencies) {
            if (currency.getLastPrice() < firstPrice) {
                firstPrice = currency.getLastPrice();
                currencyToReturn = currency;
            }
        }

        return currencyToReturn;
    }

    public Currency findMaxByName(String currName) throws Exception {
        if (!Arrays.asList(Currency.possibleCurrencyNames).contains(currName)) {
            throw new Exception("invalid parameters (possible: BTC, ETH, XRP)");
        }

        List<Currency> currencies = getCurrenciesByCurrName(currName);

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

    public List<Currency> getCurrenciesByCurrName(String currName) {
        return currencyRepo.findByCurrName(currName);
    }

    public Currency getCurrencyFromApi(String currencyName) throws Exception {
        if (!Arrays.asList(Currency.possibleCurrencyNames).contains(currencyName)) {
            throw new Exception("invalid parameters (possible: BTC, ETH, XRP)");
        }
        return createCurrencyFromDto(currencyClient.getCurrencyDtoFromApi(currencyName));
    }

    private Currency createCurrencyFromDto(@NotNull CurrencyDTO input) {
        return new Currency(input.getCurr1(),
                Double.parseDouble(input.getLprice()),
                Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
        );
    }
}

package com.example.javatesttask.service;

import com.example.javatesttask.domain.Currency;
import com.example.javatesttask.domain.CurrencyDTO;
import com.example.javatesttask.repository.CurrencyRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepo currencyRepo;
    @Autowired
    CurrencyClient currencyClient;

    public Currency findMinByName(String currName) {
        @NotNull
        List<Currency> currencies = getCurrenciesByCurrName(currName);

        Currency currencyToReturn;

//        if (currencies.size() == 0) {
//            currencyToReturn = null;
//            Optional<Currency> opt = Optional.ofNullable(currencyToReturn);
//            return opt;
//        }
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

    public Currency findMaxByName(String currName) {
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

    public Currency getCurrencyAPI() {
        return toCurrency(currencyClient.getCurr());
    }

    private Currency toCurrency(@NotNull CurrencyDTO input) {
        return new Currency(input.getCurr1(),
                Double.parseDouble(input.getLprice()),
                Timestamp.valueOf(LocalDateTime.now())
        );
    }
}

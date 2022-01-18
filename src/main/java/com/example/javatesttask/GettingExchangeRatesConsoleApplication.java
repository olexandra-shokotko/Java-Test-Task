package com.example.javatesttask;

import com.example.javatesttask.domain.Currency;
import com.example.javatesttask.repository.CurrencyRepo;
import com.example.javatesttask.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@Profile("import")
public class GettingExchangeRatesConsoleApplication implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(GettingExchangeRatesConsoleApplication.class);

    @Autowired
    CurrencyService currencyService;
    @Autowired
    CurrencyRepo currencyRepo;

    private List<String> currenciesNames = new ArrayList<>(Arrays.asList(Currency.possibleCurrencyNames));

    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING : command line runner");

        for (String currencyName : currenciesNames) {
            Currency currency = currencyService.getCurrencyFromApi(currencyName);
            LOG.info(currency.toString());
        }
    }
}

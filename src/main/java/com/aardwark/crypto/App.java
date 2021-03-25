package com.aardwark.crypto;

import static com.aardwark.crypto.definition.CurrencyCatalog.EUR;
import static com.aardwark.crypto.definition.CurrencyCatalog.JPY;
import static com.aardwark.crypto.definition.CurrencyCatalog.USD;

import com.aardwark.crypto.service.CryptoValueCalculator;

/**
 * Main class of Crypto calculator app.
 *
 * @author Marek Krištof
 */
public class App {

    /**
     * Main method of Crypto calculator app.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        CryptoValueCalculator.calculateAndReportValueOfPortfolioInSpecificCurrencies(EUR, JPY, USD);
    }
}

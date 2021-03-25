package com.aardwark.crypto.service;

import com.aardwark.crypto.data.Cryptocurrency;
import com.aardwark.crypto.data.ExchangeRate;
import com.aardwark.crypto.definition.CurrencyCatalog;
import com.aardwark.crypto.util.ValidationUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.aardwark.crypto.definition.Messages.ERROR_CRYPTOCURRENCY_OR_CURRENCY_IS_NULL;
import static com.aardwark.crypto.definition.Messages.ERROR_CURRENCIES_NOT_DEFINED;
import static com.aardwark.crypto.definition.Messages.ERROR_INVALID_DATA_INPUT;
import static com.aardwark.crypto.definition.Messages.LINE_BREAK;

/**
 * Crypto calculator of actual value for various currencies.
 *
 * @author Marek Krištof
 */
public class CryptoValueCalculator {

    private static final String BOBS_CRYPTO_FILE = "bobs_crypto.txt";

    private CryptoValueCalculator() {}

    /**
     * Calculate and report actual value of all cryptocurrencies in portfolio for specified currencies.
     * Portfolio is stored in file.
     *
     * @param currencies comma separated list of supported currencies from {@link CurrencyCatalog}.
     * @return value of whole portfolio
     */
    public static void calculateAndReportValueOfPortfolioInSpecificCurrencies(final CurrencyCatalog... currencies) {
        final List<Cryptocurrency> cryptocurrencies = CryptoPortfolioFileReader.readPortfolioFromFile(BOBS_CRYPTO_FILE);

        if (ValidationUtils.isCurrenciesValid(currencies)) {
            for (CurrencyCatalog currency : currencies) {
                calculateAndReportTotalValueForCurrency(cryptocurrencies, currency);
            }
        } else {
            System.err.printf(ERROR_CURRENCIES_NOT_DEFINED);
        }
    }

    public static BigDecimal calculateAndReportTotalValueForCurrency(
            final List<Cryptocurrency> cryptocurrencies,
            final CurrencyCatalog currency) {

        if (ValidationUtils.isNotNull(cryptocurrencies, currency)) {
            System.out.printf("Calculating value of portfolio in currency %s%n", currency);
            final BigDecimal totalValueOfPortfolio = cryptocurrencies.stream()
                    .map(cryptocurrency -> ExchangeRateService.enrichCryptoCurrencyByExchangeRate(cryptocurrency, currency))
                    .map(cryptocurrency -> CryptoValueCalculator.calculateAndReportCryptoValue(cryptocurrency, currency))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            reportTotalValue(totalValueOfPortfolio, currency.getShortcut());
            return totalValueOfPortfolio;
        }
        else {
            System.err.println(ERROR_CRYPTOCURRENCY_OR_CURRENCY_IS_NULL);
            return BigDecimal.ZERO;
        }
    }

    /**
     * Calculate and report actual value of for specified cryptocurrency a currency.
     *
     * @param cryptocurrency cryptocurrency to be calculated
     * @param currency currency for conversion of value
     * @return actual value of cryptocurrency in specified currency
     */
    public static BigDecimal calculateAndReportCryptoValue(
            final Cryptocurrency cryptocurrency,
            final CurrencyCatalog currency) {

        if (ValidationUtils.isDataInputForCryptoCalculationValid(cryptocurrency, currency)){
            final BigDecimal valueOfCryptocurrency = cryptocurrency.getExchangeRates().stream()
                    .filter(c -> c.getCurrency().equals(currency))
                    .map(ExchangeRate::getExchangeRate)
                    .reduce(BigDecimal.valueOf(cryptocurrency.getQuantity()), BigDecimal::multiply)
                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);

            reportSingleValue(cryptocurrency.getCryptocurrencyCatalog().getName(), valueOfCryptocurrency, currency.getShortcut());
            return valueOfCryptocurrency;
        } else {
            System.err.printf(ERROR_INVALID_DATA_INPUT);
            return BigDecimal.ZERO;
        }
    }

    private static void reportSingleValue(
            final String cryptoName,
            final BigDecimal valueOfCrypto,
            final String currency){

        System.out.printf("Actual value of crypto currency %s is %s %s%n", cryptoName, valueOfCrypto, currency);

    }

    private static void reportTotalValue(final BigDecimal totalValue, final String currency){
        System.out.printf("Total value of portfolio is %s %s%n", totalValue, currency);
        System.out.println(LINE_BREAK);
    }
}

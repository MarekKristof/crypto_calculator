package com.aardwark.crypto.service;

import com.aardwark.crypto.data.Cryptocurrency;
import com.aardwark.crypto.definition.CryptocurrencyCatalog;
import com.aardwark.crypto.definition.CurrencyCatalog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link ExchangeRateService}.
 *
 * @author Marek Krištof
 */
class ExchangeRateServiceTest {

    @Test
    void enrichCryptoCurrencyByExchangeRate_test() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);

        assertTrue(cryptocurrency.getExchangeRates().isEmpty());

        final Cryptocurrency enrichedCryptocurrency = ExchangeRateService.enrichCryptoCurrencyByExchangeRate(
                                                            cryptocurrency,
                                                            CurrencyCatalog.EUR);

        assertFalse(enrichedCryptocurrency.getExchangeRates().isEmpty());
    }

    @Test
    void enrichCryptoCurrencyByExchangeRate_nullCurrencyTest() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);

        assertTrue(cryptocurrency.getExchangeRates().isEmpty());

        final Cryptocurrency enrichedCryptocurrency = ExchangeRateService.enrichCryptoCurrencyByExchangeRate(
                cryptocurrency, null);

        assertTrue(enrichedCryptocurrency.getExchangeRates().isEmpty());
    }

    @Test
    void enrichCryptoCurrencyByExchangeRate_nullCurrencyAndCryptocurrencyTest() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);

        assertTrue(cryptocurrency.getExchangeRates().isEmpty());

        final Cryptocurrency enrichedCryptocurrency = ExchangeRateService.enrichCryptoCurrencyByExchangeRate(
                null, null);

        assertNull(enrichedCryptocurrency);
    }
}
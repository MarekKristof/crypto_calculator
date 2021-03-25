package com.aardwark.crypto.service;

import com.aardwark.crypto.data.Cryptocurrency;
import com.aardwark.crypto.data.ExchangeRate;
import com.aardwark.crypto.definition.CryptocurrencyCatalog;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static com.aardwark.crypto.definition.CurrencyCatalog.EUR;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link CryptoValueCalculator}.
 *
 * @author Marek Krištof
 */
class CryptoValueCalculatorTest {

    @Test
    void calculateAndReportTotalValueOfPortfolioForSelectedCurrency_test() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);
        final BigDecimal totalValue = CryptoValueCalculator
                .calculateAndReportTotalValueForCurrency(Collections.singletonList(cryptocurrency), EUR);

        assertNotNull(totalValue);
        assertTrue(totalValue.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void calculateAndReportTotalValueOfPortfolioForSelectedCurrency_nullValuesTest() {
        final BigDecimal totalValue = CryptoValueCalculator.calculateAndReportTotalValueForCurrency(null, null);

        assertNotNull(totalValue);
        assertEquals(totalValue.compareTo(BigDecimal.ZERO), 0);

    }

    @Test
    void calculateAndReportTotalValueOfPortfolioForSelectedCurrency_emptyCryptoCurrenciesTest() {
        final BigDecimal totalValue = CryptoValueCalculator.calculateAndReportTotalValueForCurrency(Collections.emptyList(), EUR);

        assertNotNull(totalValue);
        assertEquals(totalValue.compareTo(BigDecimal.ZERO), 0);
    }

    @Test
    void calculateAndReportCryptoValue_test() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);
        cryptocurrency.addExchangeRate(new ExchangeRate(EUR, BigDecimal.TEN));

        final BigDecimal cryptoValue = CryptoValueCalculator
                                                .calculateAndReportCryptoValue(cryptocurrency, EUR);

        assertNotNull(cryptoValue);
        assertEquals(BigDecimal.valueOf(10).setScale(2, BigDecimal.ROUND_HALF_EVEN), cryptoValue);
    }

    @Test
    void calculateAndReportCryptoValueEmptyExchangeRate_test() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);

        final BigDecimal cryptoValue = CryptoValueCalculator
                                            .calculateAndReportCryptoValue(cryptocurrency, EUR);

        assertNotNull(cryptoValue);
        assertEquals(BigDecimal.ZERO, cryptoValue);
    }

    @Test
    void calculateAndReportCryptoValueNegativeQuantity_test() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, -1);
        cryptocurrency.addExchangeRate(new ExchangeRate(EUR, BigDecimal.TEN));

        final BigDecimal cryptoValue = CryptoValueCalculator
                .calculateAndReportCryptoValue(cryptocurrency, EUR);

        assertNotNull(cryptoValue);
        assertEquals(BigDecimal.ZERO, cryptoValue);
    }
}
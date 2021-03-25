package com.aardwark.crypto.util;

import com.aardwark.crypto.data.Cryptocurrency;
import com.aardwark.crypto.data.ExchangeRate;
import com.aardwark.crypto.definition.CryptocurrencyCatalog;
import com.aardwark.crypto.definition.CurrencyCatalog;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link ValidationUtils}.
 *
 * @author Marek Krištof
 */
class ValidationUtilsTest {

    @Test
    void isCryptoRecordValid_test() {
        assertTrue(ValidationUtils.isCryptoRecordValid("BTC=1236"));
        assertTrue(ValidationUtils.isCryptoRecordValid("ASD=0"));
        assertTrue(ValidationUtils.isCryptoRecordValid("QWE=1236"));
    }

    @Test
    void isCryptoRecordValid_negativeTest() {
        assertFalse(ValidationUtils.isCryptoRecordValid("ABC="));
        assertFalse(ValidationUtils.isCryptoRecordValid("ABC465"));
        assertFalse(ValidationUtils.isCryptoRecordValid("=777"));
        assertFalse(ValidationUtils.isCryptoRecordValid("B=1236"));
    }

    @Test
    void isCryptoNameSymbolKnown_test() {
        assertTrue(ValidationUtils.isCryptoNameSymbolKnown("BTC"));
        assertTrue(ValidationUtils.isCryptoNameSymbolKnown("ETH"));
        assertTrue(ValidationUtils.isCryptoNameSymbolKnown("XRP"));
    }

    @Test
    void isCryptoNameSymbolKnown_negativeTest() {
        assertFalse(ValidationUtils.isCryptoNameSymbolKnown("B"));
        assertFalse(ValidationUtils.isCryptoNameSymbolKnown("AER"));
        assertFalse(ValidationUtils.isCryptoNameSymbolKnown("QWE"));
    }

    @Test
    void isDataInputForCryptoCalculationValid_test() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);
        cryptocurrency.addExchangeRate(new ExchangeRate(CurrencyCatalog.EUR, BigDecimal.TEN));
        assertTrue(ValidationUtils.isDataInputForCryptoCalculationValid(cryptocurrency, CurrencyCatalog.EUR));
    }

    @Test
    void isDataInputForCryptoCalculationValid_negativeQuantityTest() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, -1);
        cryptocurrency.addExchangeRate(new ExchangeRate(CurrencyCatalog.EUR, BigDecimal.TEN));
        assertFalse(ValidationUtils.isDataInputForCryptoCalculationValid(cryptocurrency, CurrencyCatalog.EUR));
    }

    @Test
    void isDataInputForCryptoCalculationValid_emptyExchangeRateTest() {
        final Cryptocurrency cryptocurrency = new Cryptocurrency(CryptocurrencyCatalog.BTC, 1);
        assertFalse(ValidationUtils.isDataInputForCryptoCalculationValid(cryptocurrency, CurrencyCatalog.EUR));
    }

    @Test
    void isCurrenciesValid_test() {
        assertTrue(ValidationUtils.isCurrenciesValid(CurrencyCatalog.JPY));
    }

    @Test
    void isCurrenciesValid_nullCurrenciesTest() {
        assertFalse(ValidationUtils.isCurrenciesValid(null));
    }

    @Test
    void isExchangeRateValid_test() {
        final ExchangeRate exchangeRate = new ExchangeRate(CurrencyCatalog.EUR, BigDecimal.TEN);
        assertTrue(ValidationUtils.isExchangeRateValid(exchangeRate));
    }

    @Test
    void isExchangeRateValid_negativeTest() {
        final ExchangeRate exchangeRate = new ExchangeRate(CurrencyCatalog.EUR, BigDecimal.valueOf(-10));
        assertFalse(ValidationUtils.isExchangeRateValid(exchangeRate));
    }
}
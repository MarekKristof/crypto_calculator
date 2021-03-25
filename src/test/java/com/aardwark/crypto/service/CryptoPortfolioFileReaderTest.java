package com.aardwark.crypto.service;

import com.aardwark.crypto.data.Cryptocurrency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link CryptoPortfolioFileReader}.
 *
 * @author Marek Krištof
 */
class CryptoPortfolioFileReaderTest {

    private static final String BOBS_CRYPTO_FILE = "bobs_crypto_test.txt";
    private static final String BOBS_CRYPTO_INVALID_FORMAT_FILE = "bobs_crypto_invalid_format_test.txt";

    @Test
    void readFileWithCryptocurrencies_test() {
        final List<Cryptocurrency> cryptocurrencies = CryptoPortfolioFileReader.readPortfolioFromFile(BOBS_CRYPTO_FILE);
        assertFalse(cryptocurrencies.isEmpty());
    }

    @Test
    void readFileWithCryptocurrencies_nullFileTest() {
        final List<Cryptocurrency> cryptocurrencies = CryptoPortfolioFileReader.readPortfolioFromFile(null);
        assertTrue(cryptocurrencies.isEmpty());
    }

    @Test
    void readFileWithCryptocurrencies_unknownFileTest() {
        final List<Cryptocurrency> cryptocurrencies = CryptoPortfolioFileReader.readPortfolioFromFile("null");
        assertTrue(cryptocurrencies.isEmpty());
    }

    @Test
    void readFileWithCryptocurrencies_invalidFormatTest() {
        final List<Cryptocurrency> cryptocurrencies = CryptoPortfolioFileReader.readPortfolioFromFile(BOBS_CRYPTO_INVALID_FORMAT_FILE);
        assertTrue(cryptocurrencies.isEmpty());
    }
}
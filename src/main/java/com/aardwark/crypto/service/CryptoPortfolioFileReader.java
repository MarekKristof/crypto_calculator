package com.aardwark.crypto.service;

import com.aardwark.crypto.data.Cryptocurrency;
import com.aardwark.crypto.definition.CryptocurrencyCatalog;
import com.aardwark.crypto.util.ValidationUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.aardwark.crypto.definition.Messages.ERROR_CRYPTOCURRENCY_IS_NOT_VALID;
import static com.aardwark.crypto.definition.Messages.ERROR_LOAD_DATA_PROBLEM;
import static com.aardwark.crypto.definition.Messages.ERROR_PORTFOLIO_FILE_IS_NULL;
import static com.aardwark.crypto.definition.Messages.ERROR_UNKNOWN_CRYPTOCURRENCY_VALID;

/**
 * Crypto portfolio reader.
 *
 * @author Marek Krištof
 */
public class CryptoPortfolioFileReader {

    private CryptoPortfolioFileReader() {}

    /**
     * This program is used for read cryptocurrency portfolio from file.
     *
     * @return list of cryptocurrencies from file.
     */
    public static List<Cryptocurrency> readPortfolioFromFile(final String cryptoPortfolioFile) {
        final List<Cryptocurrency> cryptocurrencies = new ArrayList<>();

        if (ValidationUtils.isNotEmpty(cryptoPortfolioFile)) {
            try {
                final ClassLoader classLoader = CryptoPortfolioFileReader.class.getClassLoader();
                final InputStream resourceAsStream = classLoader.getResourceAsStream(cryptoPortfolioFile);

                if (resourceAsStream != null) {
                    final BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));

                    String line;
                    while ((line = br.readLine()) != null) {
                        validateAndReturnCryptocurrencyRecord(line).ifPresent(cryptocurrencies::add);
                    }
                    br.close();
                } else {
                    System.err.printf(ERROR_LOAD_DATA_PROBLEM, cryptoPortfolioFile);
                }

            } catch (IOException e) {
                System.err.printf(ERROR_LOAD_DATA_PROBLEM, cryptoPortfolioFile);
            }
        }else {
            System.err.println(ERROR_PORTFOLIO_FILE_IS_NULL);
        }
        return cryptocurrencies;
    }

    private static Optional<Cryptocurrency> validateAndReturnCryptocurrencyRecord(final String line) {
        if (ValidationUtils.isCryptoRecordValid(line)) {
            final String symbol = line.substring(0, 3);
            if (ValidationUtils.isCryptoNameSymbolKnown(symbol)) {
                final CryptocurrencyCatalog cryptocurrency = getCryptoShortcut(symbol);
                final Integer quantity = Integer.valueOf(line.substring(4));

                return Optional.of(new Cryptocurrency(cryptocurrency, quantity));

            } else {
                System.out.printf(ERROR_UNKNOWN_CRYPTOCURRENCY_VALID, line);
            }
        } else {
            System.err.printf(ERROR_CRYPTOCURRENCY_IS_NOT_VALID, line);
        }
        return Optional.empty();
    }

    private static CryptocurrencyCatalog getCryptoShortcut(final String shortcut) {
        return CryptocurrencyCatalog.valueOf(shortcut);
    }
}


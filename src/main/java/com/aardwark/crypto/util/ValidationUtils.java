package com.aardwark.crypto.util;

import com.aardwark.crypto.data.Cryptocurrency;
import com.aardwark.crypto.data.ExchangeRate;
import com.aardwark.crypto.definition.CryptocurrencyCatalog;
import com.aardwark.crypto.definition.CurrencyCatalog;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Validations used in crypto calculator.
 *
 * @author Marek Krištof
 */
public class ValidationUtils {

    private static final Pattern CRYPTO_RECORD_PATTERN = Pattern.compile("[A-Z]{3}=\\d+");

    private ValidationUtils() {
    }

    /**
     * This method is used for validation of crypto record format.
     *
     * @param cryptoRecord which will be validated.
     * @return true, if crypto record is valid. Otherwise false.
     */
    public static boolean isCryptoRecordValid(final String cryptoRecord) {
        return CRYPTO_RECORD_PATTERN.matcher(cryptoRecord.trim()).matches();
    }

    /**
     * This method is used for checking the crypto name symbol.
     *
     * @param cryptoNameSymbol represents short code of crypto, which will be check, if it is known crypto.
     * @return true, if currency is known. otherwise false.
     */
    public static boolean isCryptoNameSymbolKnown(final String cryptoNameSymbol) {
        return Stream.of(CryptocurrencyCatalog.values()).map(CryptocurrencyCatalog::getShortcut).collect(Collectors.toSet()).contains(cryptoNameSymbol);
    }

    /**
     * Validate if cryptocurrency and currency is valid.
     *
     * @param cryptocurrency cryptocurrency
     * @param currency currency
     * @return true if parameters are valid. Otherwise false.
     */
    public static boolean isDataInputForCryptoCalculationValid(
            final Cryptocurrency cryptocurrency,
            final CurrencyCatalog currency) {

        return cryptocurrency.getQuantity() > 0 && !cryptocurrency.getExchangeRates().isEmpty() && currency != null;
    }

    /**
     * Validate if currencies are valid.
     *
     * @param currencies currencies
     * @return true if parameters are valid. Otherwise false.
     */
    public static boolean isCurrenciesValid(final CurrencyCatalog... currencies) {
        return currencies != null && currencies.length > 0;
    }

    /**
     * Validate if exchange rate is valid.
     *
     * @param exchangeRate exchangeRate
     * @return true if parameter is valid. Otherwise false.
     */
    public static boolean isExchangeRateValid(final ExchangeRate exchangeRate) {
        return exchangeRate.getExchangeRate().compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Validate if objects are not null.
     *
     * @param objects array of objects to be validated.
     * @return true if parameters are not null. Otherwise false.
     */
    public static boolean isNotNull(final Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    /**
     *  Validate if String value is not empty.
     *
     * @param value value to be validated.
     * @return true if string is not empty. Otherwise false.
     */
    public static boolean isNotEmpty(final String value) {
        return value != null && value.length() > 0;
    }
}

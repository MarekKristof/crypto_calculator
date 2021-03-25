package com.aardwark.crypto.service;

import com.aardwark.crypto.data.Cryptocurrency;
import com.aardwark.crypto.data.ExchangeRate;
import com.aardwark.crypto.definition.CurrencyCatalog;
import com.aardwark.crypto.util.ValidationUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.aardwark.crypto.definition.Messages.ERROR_CRYPTOCURRENCY_OR_CURRENCY_IS_NULL;

/**
 * Exchange rate service is used to enriching currency about actual value of selected currency.
 *
 * @author Marek Krištof
 */
public class ExchangeRateService {

    private ExchangeRateService() {}

    public static Cryptocurrency enrichCryptoCurrencyByExchangeRate(final Cryptocurrency cryptocurrency, final CurrencyCatalog currency) {

        if (ValidationUtils.isNotNull(cryptocurrency, currency)) {
            try {
                final String cryptoShortcut = cryptocurrency.getCryptocurrencyCatalog().getShortcut();
                URL url = new URL("https://min-api.cryptocompare.com/data/price?fsym=" + cryptoShortcut + "&tsyms=" + currency.getShortcut());

                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
                }
                final InputStreamReader in = new InputStreamReader(connection.getInputStream());

                final BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    final BigDecimal exchangeRate = getActualExchangeRare(output);
                    final ExchangeRate rate = new ExchangeRate(currency, exchangeRate);
                    cryptocurrency.addExchangeRate(rate);
                }
                connection.disconnect();

            } catch (final Exception e) {
                System.out.println("Exception in NetClientGet:- " + e);
            }
        }else{
            System.out.println(ERROR_CRYPTOCURRENCY_OR_CURRENCY_IS_NULL);
        }
        return cryptocurrency;
    }

    private static BigDecimal getActualExchangeRare(final String output) {
        final String value = output.replaceAll("[{}\"]", "");
        return new BigDecimal(value.substring(4)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}

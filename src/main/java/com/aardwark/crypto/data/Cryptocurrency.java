package com.aardwark.crypto.data;

import com.aardwark.crypto.definition.CryptocurrencyCatalog;
import com.aardwark.crypto.util.ValidationUtils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static com.aardwark.crypto.definition.Messages.ERROR_EXCHANGE_RATE_INVALID;

/**
 * Wrapper class, which represents crypto from portfolio.
 *
 * @author Marek Krištof
 */
public class Cryptocurrency {

    private CryptocurrencyCatalog cryptocurrencyCatalog;
    private int quantity;
    private final Set<ExchangeRate> exchangeRates = new LinkedHashSet<>();

    public Cryptocurrency(final CryptocurrencyCatalog cryptoNameSymbol, int quantity) {
        this.cryptocurrencyCatalog = cryptoNameSymbol;
        this.quantity = quantity;
    }

    public CryptocurrencyCatalog getCryptocurrencyCatalog() {
        return cryptocurrencyCatalog;
    }

    public void setCryptocurrencyCatalog(final CryptocurrencyCatalog cryptocurrencyCatalog) {
        this.cryptocurrencyCatalog = cryptocurrencyCatalog;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<ExchangeRate> getExchangeRates() {
        return Collections.unmodifiableSet(exchangeRates);
    }

    public void addExchangeRate(final ExchangeRate exchangeRate) {
        if (ValidationUtils.isExchangeRateValid(exchangeRate)){
            this.exchangeRates.add(exchangeRate);
        }else{
            System.out.printf(ERROR_EXCHANGE_RATE_INVALID);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Cryptocurrency that = (Cryptocurrency) o;
        return quantity == that.quantity &&
                cryptocurrencyCatalog == that.cryptocurrencyCatalog &&
                Objects.equals(exchangeRates, that.exchangeRates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptocurrencyCatalog, quantity, exchangeRates);
    }

    @Override
    public String toString() {
        return "Cryptocurrency{" +
                "cryptocurrencyCatalog=" + cryptocurrencyCatalog +
                ", quantity=" + quantity +
                '}';
    }
}

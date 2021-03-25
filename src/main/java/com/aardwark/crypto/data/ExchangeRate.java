package com.aardwark.crypto.data;

import com.aardwark.crypto.definition.CurrencyCatalog;

import java.math.BigDecimal;

/**
 * @author Marek Krištof
 */
public class ExchangeRate {

    private CurrencyCatalog currency;

    private BigDecimal exchangeRate;

    public ExchangeRate(final CurrencyCatalog currency, final BigDecimal exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public CurrencyCatalog getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCatalog currency) {
        this.currency = currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}

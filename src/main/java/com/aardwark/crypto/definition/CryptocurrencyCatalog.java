package com.aardwark.crypto.definition;

/**
 * Enum of well known crypto name symbols.
 *
 * @author Marek Krištof
 */
public enum CryptocurrencyCatalog {

    BTC(1, "Bitcoin", "BTC"),
    ETH(2, "Ethereum", "ETH"),
    XRP(3, "Ripple", "XRP");

    private final int index;
    private final String name;
    private final String shortcut;

    CryptocurrencyCatalog(int index, final String name, final String shortcut) {
        this.index = index;
        this.name = name;
        this.shortcut = shortcut;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getShortcut() {
        return shortcut;
    }
}

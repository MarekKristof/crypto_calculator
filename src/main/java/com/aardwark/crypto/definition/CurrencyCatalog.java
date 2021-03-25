package com.aardwark.crypto.definition;

/**
 * @author Marek Krištof
 */
public enum CurrencyCatalog {

    EUR(1, "Euro", "EUR"),
    USD(2, "US dollar", "USD"),
    JPY(3, "Japanese yen", "JPY");

    private int index;
    private final String name;
    private final String shortcut;

    CurrencyCatalog(int index, final String name, final String shortcut) {
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

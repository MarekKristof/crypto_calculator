package com.aardwark.crypto.definition;

/**
 * List of messages used for reporting.
 *
 * @author Marek Krištof
 */
public class Messages {

    private Messages() {
    }

    public static final String LINE_BREAK = "-------------------------------------------------------------";

    public static final String ERROR_LOAD_DATA_PROBLEM = "Problem occurs during loading data from file. Please check if resources directory contains this file: %s%n";
    public static final String ERROR_CRYPTOCURRENCY_IS_NOT_VALID = "Record %s from portfolio is nof valid%n";
    public static final String ERROR_UNKNOWN_CRYPTOCURRENCY_VALID = "Cryptocurrency from record %s is not known%n";
    public static final String ERROR_INVALID_DATA_INPUT = "Data input for validation is invalid%n";
    public static final String ERROR_CURRENCIES_NOT_DEFINED = "Currencies are not defined%n";
    public static final String ERROR_EXCHANGE_RATE_INVALID = "Exchange rate is not valid%n";
    public static final String ERROR_CRYPTOCURRENCY_OR_CURRENCY_IS_NULL= "Cryptocurrency or currency is null";
    public static final String ERROR_PORTFOLIO_FILE_IS_NULL= "Portfolio file is null";
}

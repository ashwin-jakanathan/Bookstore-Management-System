package BookStore.utils;

import java.text.DecimalFormat;

/**
 * Utility class to format prices.
 * Ensures that all prices are displayed with two decimal places and a dollar sign.
 *
 * Abstraction Function:
 * - Formats a decimal number into a dollar string like "$50.00".
 *
 * Representation Invariant:
 * - All formatted prices will always have exactly two digits after the decimal point.
 */
public class PriceFormatter {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    /**
     * Converts a double price into a string with two decimal places and a dollar sign.
     *
     * Requires: price is a valid number (≥ 0 is recommended).
     * Modifies: none
     * Effects: Returns a formatted price string like "$100.00".
     */
    public static String formatPrice(double price) {
        return "$" + decimalFormat.format(price);
    }
}

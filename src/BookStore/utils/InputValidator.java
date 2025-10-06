package BookStore.utils;

import BookStore.models.Book;
import BookStore.models.Customer;
import java.util.List;
import java.util.Map;

/**
 * Utility class to validate user input fields like usernames, passwords, prices, etc.
 *
 * Abstraction Function:
 * - Helps check if user input is empty, numeric, or already exists.
 * - Ensures that only valid usernames, passwords, and prices are accepted.
 *
 * Representation Invariant:
 * - All input checks return a boolean.
 * - None of the utility methods should modify program state.
 */
public class InputValidator {

    /**
     * Checks if a given string input is empty or only contains spaces.
     *
     * Requires: input can be null or a string
     * Modifies: none
     * Effects: Returns true if the input is null or just spaces, false otherwise.
     */
    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    /**
     * Checks if a given string is a valid positive number.
     *
     * Requires: input is not null
     * Modifies: none
     * Effects: Returns true if the input is a number greater than 0, false otherwise.
     */
    public static boolean isNumeric(String input) {
        if (isEmpty(input)) return false;
        try {
            Double.parseDouble(input);
            return Double.parseDouble(input) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a username meets basic rules.
     *
     * Requires: username is not null
     * Modifies: none
     * Effects: Returns true if the username has at least 3 characters and is not empty.
     */
    public static boolean isValidUsername(String username) {
        return !isEmpty(username) && username.length() >= 3;
    }

    /**
     * Validates if a password meets basic rules.
     *
     * Requires: password is not null
     * Modifies: none
     * Effects: Returns true if the password has at least 6 characters and is not empty.
     */
    public static boolean isValidPassword(String password) {
        return !isEmpty(password) && password.length() >= 6;
    }

    /**
     * Checks if a price is one of the allowed bookstore values.
     *
     * Requires: price ≥ 0
     * Modifies: none
     * Effects: Returns true if the price is exactly 50, 100, 200, or 500.
     */
    public static boolean isValidBookPrice(double price) {
        return price == 50 || price == 100 || price == 200 || price == 500;
    }

    /**
     * Checks if a book title already exists in the given list of books.
     *
     * Requires: books list is not null, title is not null
     * Modifies: none
     * Effects: Returns true if a book with the same title (case-insensitive) exists.
     */
    public static boolean bookAlreadyExists(List<Book> books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a username already exists in the map of customers.
     *
     * Requires: customers map is not null, username is not null
     * Modifies: none
     * Effects: Returns true if the username is already in the customer map.
     */
    public static boolean userAlreadyExists(Map<String, Customer> customers, String username) {
        return customers.containsKey(username.trim().toLowerCase());
    }
}

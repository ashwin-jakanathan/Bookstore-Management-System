package BookStore.handlers;

import BookStore.models.Customer;
import BookStore.models.Owner;
import BookStore.database.DatabaseManager;

/**
 * Handles user authentication logic for the bookstore.
 *
 * Abstraction Function:
 * - Validates whether a user is an owner (admin) or a customer based on credentials.
 *
 * Representation Invariant:
 * - dbManager must be initialized properly.
 * - "admin" is treated as a hardcoded owner username.
 */
public class LoginHandler {
    private DatabaseManager dbManager = new DatabaseManager();

    /**
     * Validates login credentials.
     *
     * Requires: username and password are not null.
     * Modifies: none
     * Effects:
     * - Returns "owner" if the credentials match the hardcoded admin.
     * - Returns "customer" if the credentials match a registered customer.
     * - Returns "invalid" if the login fails.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return A string representing the role: "owner", "customer", or "invalid".
     */
    public String validateUser(String username, String password) {
        // Hardcoded admin login
        if (username.equals("admin") && password.equals("admin")) {
            return "owner";  // Admin role
        }

        // Check if the username exists as a customer
        Customer customer = dbManager.getCustomer(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return "customer";  // Valid customer
        }

        return "invalid";  // Authentication failed
    }
}

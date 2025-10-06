package BookStore.handlers;

import BookStore.models.Book;
import BookStore.models.Customer;
import BookStore.database.DatabaseManager;
import BookStore.utils.InputValidator;

import java.util.List;
import java.util.Map;

/**
 * Handles bookstore owner actions such as managing books and customers.
 *
 * Abstraction Function:
 * - This class allows the owner to add or remove books and customers,
 *   and update customer balances.
 *
 * Representation Invariant:
 * - databaseManager must be initialized.
 * - Cannot add books or users that already exist.
 */
public class OwnerHandler {
    private DatabaseManager dbManager = new DatabaseManager();

    /**
     * Adds a new book to the store if it passes validation.
     *
     * Requires: title is not null, price is one of the accepted values (50, 100, 200, 500).
     * Modifies: books.txt file
     * Effects:
     * - Returns a message based on success or failure.
     * - Does not allow adding duplicate book titles.
     *
     * @param title The title of the book.
     * @param price The price of the book.
     * @return Result message indicating success or reason for failure.
     */
    public String addBook(String title, double price) {
        
        if (title.equals("")){
            return "Invalid book name";
        }
        
        if (!InputValidator.isValidBookPrice(price)) {
            return "Invalid book price. Must be 50, 100, 200, or 500.";
        }

        List<Book> books = dbManager.loadBooks();
        if (InputValidator.bookAlreadyExists(books, title)) {
            return "Book already exists. Cannot add duplicate.";
        }

        Book book = new Book(title, price);
        dbManager.addBook(book);
        return "Book added successfully.";
    }

    /**
     * Removes a book from the store based on its title.
     *
     * Requires: title is not null
     * Modifies: books.txt file
     * Effects: Deletes the book from the file if it exists.
     *
     * @param title The title of the book to remove.
     */
    public String removeBook(String title) {
        List<Book> books = dbManager.loadBooks();
        if (!InputValidator.bookAlreadyExists(books, title)) {
            return "Book does not exist";
        }
        
        dbManager.removeBook(title);
        return "Book removed successfully.";
    }

    /**
     * Adds a new customer to the system.
     *
     * Requires: username and password are not null, balance ≥ 0
     * Modifies: users.txt file
     * Effects:
     * - Adds a customer if the username is unique and not "admin".
     * - Returns a success or error message.
     *
     * @param username The customer's username.
     * @param password The customer's password.
     * @param balance  Starting balance for the customer.
     * @return Message indicating if the user was added or why it failed.
     */
    public String addCustomer(String username, String password, double balance) {
        // Prevent creating a user with the admin username
        if (username.equalsIgnoreCase("admin")) {
            return "Cannot use 'admin' as a username. It is reserved.";
        }
        
        if (username.equalsIgnoreCase("")) {
            return "Invalid username entered";
        }

        Map<String, Customer> customers = dbManager.loadCustomers();
        if (InputValidator.userAlreadyExists(customers, username)) {
            return "Username already exists. Cannot add user.";
        }

        Customer customer = new Customer(username, password, balance);
        dbManager.addCustomer(customer);
        return "User added successfully.";
    }

    /**
     * Removes a customer by username.
     *
     * Requires: username is not null
     * Modifies: users.txt file
     * Effects: Removes the customer if they exist.
     *
     * @param username The username of the customer to remove.
     */
    public String removeCustomer(String username) {
        Map<String, Customer> customers = dbManager.loadCustomers();
        if (!InputValidator.userAlreadyExists(customers, username)) {
            return "User does not exist. Cannot remove user.";
        }
        
        dbManager.removeCustomer(username);
        return "User removed successfully.";
    }

    /**
     * Updates the balance of a specific customer.
     *
     * Requires: username is valid, amount ≥ 0
     * Modifies: users.txt file
     * Effects: Sets the customer's balance to the new value.
     *
     * @param username The username of the customer.
     * @param amount   The new balance to set.
     */
    public String adjustCustomerBalance(String username, double amount) {
        Map<String, Customer> customers = dbManager.loadCustomers();
        if (!InputValidator.userAlreadyExists(customers, username)) {
            return "User does not exist. Cannot update balance.";
        }
        
        if (amount < 0){
            return "Invalid amount";
        }
        
        dbManager.updateCustomerBalance(username, amount);
        return "Balance updated successfully.";
    }
}

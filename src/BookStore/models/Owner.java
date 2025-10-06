package BookStore.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the bookstore owner.
 * The owner can manage books and customers in the system.
 *
 * Abstraction Function:
 * - The owner has a username and password.
 * - The owner can add/remove books and customers, and update customer balances.
 *
 * Representation Invariant:
 * - books and customers lists should not be null.
 * - No duplicate usernames in the customers list.
 * - No duplicate book titles in the books list.
 */
public class Owner extends User {
    private List<Book> books;
    private List<Customer> customers;

    /**
     * Creates an owner with a given username and password.
     *
     * Requires: username and password are not null.
     * Modifies: this
     * Effects: Initializes the owner with empty book and customer lists.
     */
    public Owner(String username, String password) {
        super(username, password);
        this.books = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    /**
     * Adds a book to the store.
     *
     * Requires: book is not null.
     * Modifies: books
     * Effects: Appends the given book to the store's list of books.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the store.
     *
     * Requires: book is not null.
     * Modifies: books
     * Effects: Removes the specified book if it exists and returns true.
     *          Returns false if the book is not found.
     */
    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    /**
     * Adds a customer to the store.
     *
     * Requires: customer is not null.
     * Modifies: customers
     * Effects: Adds the given customer to the customer list.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Removes a customer by username.
     *
     * Requires: username is not null.
     * Modifies: customers
     * Effects: Removes the customer with the given username and returns true.
     *          Returns false if the customer is not found.
     */
    public boolean removeCustomer(String username) {
        return customers.removeIf(c -> c.getUsername().equals(username));
    }

    /**
     * Updates the balance for a specific customer.
     *
     * Requires: username is not null, amount ≥ 0
     * Modifies: customer's balance
     * Effects: Sets the customer's balance to the new amount and returns true.
     *          Returns false if the customer is not found.
     */
    public boolean adjustCustomerBalance(String username, double amount) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                customer.setBalance(amount);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representing the owner.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns a string like "Admin: adminName"
     */
    @Override
    public String toString() {
        return "Admin: " + username;
    }
}

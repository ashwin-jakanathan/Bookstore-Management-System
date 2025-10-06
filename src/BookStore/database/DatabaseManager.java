package BookStore.database;

import BookStore.models.Book;
import BookStore.models.Customer;
import java.io.*;
import java.util.*;

/**
 * Manages reading and writing data to files for books and customers.
 *
 * Abstraction Function:
 * - Handles all file operations (loading, saving, updating) for persistent storage.
 * - Supports both books and customer data.
 *
 * Representation Invariant:
 * - The file paths must exist and be accessible for reading/writing.
 * - No null values should be saved into the files.
 */
public class DatabaseManager {
    private static final String BOOKS_FILE = "src/BookStore/resources/Books.txt";
    private static final String CUSTOMERS_FILE = "src/BookStore/resources/Users.txt";

    /**
     * Loads books from the books.txt file.
     *
     * Requires: books.txt file exists and is readable.
     * Modifies: none
     * Effects: Returns a list of all books saved in the file.
     */
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String title = data[0].trim();
                    double price = Double.parseDouble(data[1].trim());
                    books.add(new Book(title, price));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
        return books;
    }

    /**
     * Saves a list of books to the books.txt file.
     *
     * Requires: books list is not null.
     * Modifies: books.txt file
     * Effects: Overwrites the file with the current list of books.
     */
    public void saveBooks(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to books file: " + e.getMessage());
        }
    }

    /**
     * Loads customer data from users.txt file.
     *
     * Requires: users.txt exists and is readable.
     * Modifies: none
     * Effects: Returns a map of customers, where key = username.
     */
    public Map<String, Customer> loadCustomers() {
        Map<String, Customer> customers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String username = parts[0];
                    String password = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    int points = Integer.parseInt(parts[3]);

                    Customer c = new Customer(username, password, balance);
                    c.setPoints(points); // Restore saved points
                    customers.put(username, c);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customers file: " + e.getMessage());
        }
        return customers;
    }

    /**
     * Saves customer data to users.txt file.
     *
     * Requires: customers map is not null.
     * Modifies: users.txt file
     * Effects: Overwrites the file with all customer records.
     */
    public void saveCustomers(Map<String, Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer c : customers.values()) {
                String line = c.getUsername() + "," + c.getPassword() + "," + c.getBalance() + "," + c.getPoints();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    /**
     * Adds a new book to the file.
     *
     * Requires: book is not null.
     * Modifies: books.txt file
     * Effects: Appends the new book to the file.
     */
    public void addBook(Book book) {
        List<Book> books = loadBooks();
        books.add(book);
        saveBooks(books);
    }

    /**
     * Removes a book from the file based on its title.
     *
     * Requires: title is not null.
     * Modifies: books.txt file
     * Effects: Deletes the book from the list and updates the file.
     */
    public void removeBook(String title) {
        List<Book> books = loadBooks();
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        saveBooks(books);
    }

    /**
     * Adds a new customer to the file.
     *
     * Requires: customer is not null.
     * Modifies: users.txt file
     * Effects: Adds the customer to the map and saves the updated list.
     */
    public void addCustomer(Customer customer) {
        Map<String, Customer> customers = loadCustomers();
        customers.put(customer.getUsername(), customer);
        saveCustomers(customers);
    }

    /**
     * Removes a customer from the file.
     *
     * Requires: username is not null.
     * Modifies: users.txt file
     * Effects: Deletes the customer from the map and updates the file.
     */
    public void removeCustomer(String username) {
        Map<String, Customer> customers = loadCustomers();
        if (customers.remove(username) != null) {
            saveCustomers(customers);
        }
    }

    /**
     * Retrieves a customer by username.
     *
     * Requires: username is not null.
     * Modifies: none
     * Effects: Returns the customer object, or null if not found.
     */
    public Customer getCustomer(String username) {
        return loadCustomers().get(username);
    }

    /**
     * Updates only the customer's balance.
     *
     * Requires: username exists in file.
     * Modifies: users.txt file
     * Effects: Replaces the customer's balance with the new amount.
     */
    public void updateCustomerBalance(String username, double newBalance) {
        Map<String, Customer> customers = loadCustomers();
        Customer customer = customers.get(username);
        if (customer != null) {
            customer.setBalance(newBalance);
            saveCustomers(customers);
        }
    }

    /**
     * Updates a customer’s full profile (balance, points, etc.).
     *
     * Requires: customer is not null.
     * Modifies: users.txt file
     * Effects: Overwrites the record for this customer in the file.
     */
    public void updateCustomer(Customer customer) {
        Map<String, Customer> customers = loadCustomers();
        customers.put(customer.getUsername(), customer);
        saveCustomers(customers);
    }

    /**
     * Deducts money from a customer's balance.
     *
     * Requires: username exists, amount ≥ 0
     * Modifies: users.txt file
     * Effects: Subtracts the amount from balance and saves changes.
     */
    public void deductBalance(String username, double amount) {
        Map<String, Customer> customers = loadCustomers();
        Customer customer = customers.get(username);
        if (customer != null && customer.deductBalance(amount)) {
            saveCustomers(customers);
        }
    }

    /**
     * Uses points and balance to complete a purchase.
     * Points are redeemed first, then balance is used for the rest.
     *
     * Requires: username exists, amount ≥ 0
     * Modifies: users.txt file
     * Effects: Updates points and balance only if the purchase is successful.
     */
    public void redeemPoints(String username, double amount) {
        Map<String, Customer> customers = loadCustomers();
        Customer customer = customers.get(username);
        if (customer != null) {
            double newCost = customer.redeemPoints(amount);
            if (customer.deductBalance(newCost)) {
                saveCustomers(customers);
            }
        }
    }
}

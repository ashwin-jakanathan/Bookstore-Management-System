package BookStore.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart that holds selected books for a customer.
 *
 * Abstraction Function:
 * - A shoppingCart is a list of books the customer wants to buy.
 * - Each book has a price, and the cart can calculate the total cost.
 *
 * Representation Invariant:
 * - books should not be null.
 * - books may contain duplicate entries if the same book is added more than once.
 */
public class ShoppingCart {
    private List<Book> books;

    /**
     * Creates a new, empty shopping cart.
     *
     * Requires: none
     * Modifies: this
     * Effects: Initializes the cart with an empty list of books.
     */
    public ShoppingCart() {
        books = new ArrayList<>();
    }

    /**
     * Adds a book to the cart.
     *
     * Requires: book is not null
     * Modifies: this
     * Effects: Adds the given book to the list of books in the cart.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the cart.
     *
     * Requires: book is not null and exists in the cart
     * Modifies: this
     * Effects: Removes the first occurrence of the book from the cart.
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /**
     * Calculates the total price of all books in the cart.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the total cost of all books currently in the cart.
     */
    public double getTotalPrice() {
        return books.stream().mapToDouble(Book::getPrice).sum();
    }

    /**
     * Clears all books from the cart.
     *
     * Requires: none
     * Modifies: this
     * Effects: Empties the shopping cart.
     */
    public void clearCart() {
        books.clear();
    }

    /**
     * Returns the list of books in the cart.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the current list of books added to the cart.
     */
    public List<Book> getBooks() {
        return books;
    }
}

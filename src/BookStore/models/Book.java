package BookStore.models;

/**
 * Represents a book in the bookstore.
 *
 * Abstraction Function:
 * - A book has a title (like "Harry Potter") and a price (like 50.0).
 * - The book object stores this basic information so it can be displayed, added to cart, or bought.
 *
 * Representation Invariant:
 * - title should not be null or empty.
 * - price should be greater than or equal to 0.
 */
public class Book {
    private String title;
    private double price;

    /**
     * Creates a new book with a title and a price.
     *
     * Requires: title is not null, and price is a positive number (≥ 0).
     * Modifies: this
     * Effects: Initializes the book with the given title and price.
     */
    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    /**
     * Gets the title of the book.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the book's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the price of the book.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the book's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the book.
     *
     * Requires: price ≥ 0
     * Modifies: this
     * Effects: Changes the book's price to the new value.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a simple string version of the book.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns a string like "Harry Potter - $50.0".
     */
    @Override
    public String toString() {
        return title + " - $" + price;
    }
}

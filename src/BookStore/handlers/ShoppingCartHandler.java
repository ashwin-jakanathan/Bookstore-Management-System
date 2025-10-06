package BookStore.handlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import BookStore.models.Book;

/**
 * Handles shopping cart operations for a customer.
 *
 * Abstraction Function:
 * - Stores the list of books selected by the customer before checkout.
 * - Allows adding, removing, viewing, and clearing items in the cart.
 *
 * Representation Invariant:
 * - cartItems should never be null.
 * - Each item in cartItems must be a valid book object.
 */
public class ShoppingCartHandler {
    private ObservableList<Book> cartItems = FXCollections.observableArrayList();

    /**
     * Adds a book to the shopping cart.
     *
     * Requires: book is not null.
     * Modifies: cartItems
     * Effects: Adds the book to the cart list.
     *
     * @param book The book to add.
     */
    public void addBook(Book book) {
        cartItems.add(book);
    }

    /**
     * Removes a book from the shopping cart.
     *
     * Requires: book is not null and exists in the cart.
     * Modifies: cartItems
     * Effects: Removes the selected book from the cart list.
     *
     * @param book The book to remove.
     */
    public void removeBook(Book book) {
        cartItems.remove(book);
    }

    /**
     * Calculates the total cost of all books in the cart.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the sum of prices for all books in the cart.
     *
     * @return Total price of books in the cart.
     */
    public double getTotalCost() {
        return cartItems.stream().mapToDouble(Book::getPrice).sum();
    }

    /**
     * Gets the list of books currently in the shopping cart.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns an observable list of cart items.
     *
     * @return List of books in the cart.
     */
    public ObservableList<Book> getCartItems() {
        return cartItems;
    }

    /**
     * Clears all items from the shopping cart.
     *
     * Requires: none
     * Modifies: cartItems
     * Effects: Empties the cart completely.
     */
    public void clearCart() {
        cartItems.clear();
    }
}

package BookStore.scenes;

import BookStore.database.DatabaseManager;
import BookStore.handlers.ShoppingCartHandler;
import BookStore.models.Book;
import BookStore.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

/**
 * Displays the shopping cart and allows customers to manage selected books.
 *
 * Abstraction Function:
 * - This scene shows available books and the user's shopping cart.
 * - Customers can add or remove books and proceed to checkout.
 *
 * Representation Invariant:
 * - cartHandler must be initialized and shared across relevant scenes.
 * - All UI elements must remain aligned and updated after every change.
 */
public class ShoppingCartScene extends VBox {
    private SceneManager sceneManager;
    private String username;
    private ShoppingCartHandler cartHandler;
    private DatabaseManager dbManager;

    /**
     * Constructs the scene that allows customers to manage their cart.
     *
     * Requires: username exists in the system, cartHandler is shared
     * Modifies: Scene view, cart contents
     * Effects: Loads customer status and all books, allows adding/removing, and continues to checkout
     *
     * @param sceneManager Scene navigation controller.
     * @param username The logged-in customer's username.
     * @param cartHandler Shared handler for managing cart items.
     */
    public ShoppingCartScene(SceneManager sceneManager, String username, ShoppingCartHandler cartHandler) {
        this.sceneManager = sceneManager;
        this.username = username;
        this.cartHandler = cartHandler;
        this.dbManager = new DatabaseManager();

        // Load customer info and points to determine status
        Map<String, Customer> customers = dbManager.loadCustomers();
        Customer current = customers.get(username);
        int points = current.getPoints();
        double balance = current.getBalance();

        String status = "None";
        if (points >= 200) {
            status = "Gold";
        } else if (points >= 100) {
            status = "Silver";
        }

        Label statusLabel = new Label("Status: " + status + ", Points: " + points);
        Label balanceLabel = new Label("Balance: " + balance);
        Label titleLabel = new Label("Available Books");
        

        // Set up the book table
        TableView<Book> bookTable = new TableView<>();
        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        TableColumn<Book, Double> priceCol = new TableColumn<>("Price");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        bookTable.getColumns().addAll(titleCol, priceCol);
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load and display all books from file
        List<Book> bookList = dbManager.loadBooks();
        ObservableList<Book> observableBooks = FXCollections.observableArrayList(bookList);
        bookTable.setItems(observableBooks);

        // Highlight books in red if they’re already in the cart
        bookTable.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book == null || empty) {
                    setStyle("");
                } else if (cartHandler.getCartItems().contains(book)) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle("-fx-text-fill: black;");
                }
            }
        });

        Button addToCartBtn = new Button("Add to Cart");

        // Cart display section
        Label cartLabel = new Label("Your Cart");
        TableView<Book> cartTable = new TableView<>();
        TableColumn<Book, String> cartTitleCol = new TableColumn<>("Title");
        TableColumn<Book, Double> cartPriceCol = new TableColumn<>("Price");
        cartTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        cartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        cartTable.getColumns().addAll(cartTitleCol, cartPriceCol);
        cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cartTable.setItems(cartHandler.getCartItems());
        
        Label cartTotalLabel = new Label("Cart Total: $0.00");
        
        updateCartTotal(cartTotalLabel);

        Button removeFromCartBtn = new Button("Remove from Cart");
        Button viewCartBtn = new Button("Checkout");
        Button backBtn = new Button("Back");

        /**
         * Adds the selected book from the table to the cart.
         * Refreshes both tables to reflect changes.
         */
        addToCartBtn.setOnAction(e -> {
            Book selected = bookTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                cartHandler.addBook(selected);
                showAlert("Book added to cart!");
                bookTable.refresh();
                cartTable.refresh();
                updateCartTotal(cartTotalLabel);
                bookTable.getSelectionModel().clearSelection();
            }
        });

        /**
         * Removes the selected book from the cart.
         * Refreshes both tables to reflect changes.
         */
        removeFromCartBtn.setOnAction(e -> {
            Book selected = cartTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                cartHandler.removeBook(selected);
                showAlert("Book removed from cart.");
                bookTable.refresh();
                cartTable.refresh();
                updateCartTotal(cartTotalLabel);
                cartTable.getSelectionModel().clearSelection();
            }
        });

        // Navigate to checkout screen
        viewCartBtn.setOnAction(e -> {
            if (cartHandler.getCartItems().isEmpty()){
                showAlert("Your cart is empty. Please add books before checkout");
            }
            else {
                sceneManager.showCustomerCostScene(username);
            }
        });
                
                

        // Return to customer dashboard
        backBtn.setOnAction(e -> sceneManager.showCustomerScene(username));

        // Layout styling
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(
                statusLabel,
                balanceLabel,
                titleLabel,
                bookTable,
                addToCartBtn,
                cartLabel,
                cartTable,
                cartTotalLabel,
                removeFromCartBtn,
                viewCartBtn,
                backBtn
        );
    }
    
    /**
     * Updates the cart total label with the current total price of all items.
     *
     * Requires: cartHandler is initialized and contains cart items. 
     * Modifies: The text of the provided Label. 
     * Effects: Calculates the total price of the cart and displays it as formatted currency.
     *
     * @param label The Label to display the cart total.
     */
    private void updateCartTotal(Label label) {
        double total = 0.0;
        for (Book b : cartHandler.getCartItems()) {
            total += b.getPrice();
        }
        label.setText("Cart Total: $" + String.format("%.2f", total));
    }

    /**
     * Displays an informational alert popup with a given message.
     *
     * @param msg The message to display.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.show();
    }
}

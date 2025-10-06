package BookStore.scenes;

import BookStore.database.DatabaseManager;
import BookStore.handlers.PaymentHandler;
import BookStore.handlers.ShoppingCartHandler;
import BookStore.models.Customer;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * Displays the final cost of books in the cart and allows the customer to make a payment.
 *
 * Abstraction Function:
 * - This scene provides the user interface for final checkout.
 * - The user can choose to pay with cash or redeem points.
 *
 * Representation Invariant:
 * - sceneManager, username, paymentHandler, and cartHandler must be initialized.
 * - All UI elements are organized vertically with proper spacing.
 */
public class CustomerCostScene extends VBox {
    private SceneManager sceneManager;
    private String username;
    private PaymentHandler paymentHandler;
    private ShoppingCartHandler cartHandler;
    private DatabaseManager dbManager;

    /**
     * Constructs the final cost screen where the customer can pay.
     *
     * Requires: username must exist, cartHandler must contain selected books
     * Modifies: UI components, customer balance and points if payment is made
     * Effects: Displays user's current status and total cost; allows payment using cash or points
     *
     * @param sceneManager The manager that handles scene transitions.
     * @param username The currently logged-in customer's username.
     * @param cartHandler The shopping cart containing selected books.
     */
    public CustomerCostScene(SceneManager sceneManager, String username, ShoppingCartHandler cartHandler) {
        this.sceneManager = sceneManager;
        this.username = username;
        this.cartHandler = cartHandler;
        this.paymentHandler = new PaymentHandler();
        this.dbManager = new DatabaseManager();

        // Load current customer details
        Map<String, Customer> customers = dbManager.loadCustomers();
        Customer current = customers.get(username);
        int points = current.getPoints();
        double balance = current.getBalance();

        // Determine and display user's reward status
        String status = "None";
        if (points >= 200) {
            status = "Gold";
        } else if (points >= 100) {
            status = "Silver";
        }
        Label statusLabel = new Label("Status: " + status + ", Points: " + points);
        Label balanceLabel = new Label("Balance: " + balance);
        // Show total cost from shopping cart
        double totalCost = cartHandler.getTotalCost();
        Label totalCostLabel = new Label("Total Cost: $" + totalCost);

        // Buttons for payment options
        Button payCashButton = new Button("Pay with Cash");
        Button payPointsButton = new Button("Redeem Points & Pay");
        Button backButton = new Button("Back");

        /**
         * Pay with cash only
         * If the balance is enough, complete the purchase, earn points, clear cart, return to customer screen.
         * Otherwise, show an error.
         */
        payCashButton.setOnAction(e -> {
            boolean success = paymentHandler.processPayment(username, totalCost, false);
            showAlert(success ? "Payment successful!" : "Insufficient funds.");
            if (success) {
                cartHandler.clearCart();
                sceneManager.showCustomerScene(username);
            }
        });

        /**
         * Pay using points first, then use balance if needed.
         * If successful, clear cart and return to main screen.
         * Otherwise, show an error and do not change user data.
         */
        payPointsButton.setOnAction(e -> {
            boolean success = paymentHandler.processPayment(username, totalCost, true);
            showAlert(success ? "Payment with points successful!" : "Insufficient funds/points.");
            if (success) {
                cartHandler.clearCart();
                sceneManager.showCustomerScene(username);
            }
        });

        // Go back to the shopping cart scene
        backButton.setOnAction(e -> sceneManager.showShoppingCartScene(username));

        // Layout setup
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(statusLabel, totalCostLabel, balanceLabel, payCashButton, payPointsButton, backButton);
    }

    /**
     * Shows a message to the user in a pop-up dialog box.
     *
     * @param msg The message to display.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.show();
    }
}

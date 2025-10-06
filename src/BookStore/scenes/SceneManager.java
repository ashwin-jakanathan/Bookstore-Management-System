package BookStore.scenes;

import BookStore.handlers.ShoppingCartHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Manages all scene transitions in the bookstore application.
 *
 * Abstraction Function:
 * - This class handles switching between different screens (login, owner, customer, cart, etc.).
 * - A shared shopping cart handler is used to preserve cart data across scenes.
 *
 * Representation Invariant:
 * - primaryStage must be initialized.
 * - cartHandler should persist between customer-related scenes.
 */
public class SceneManager {
    private Stage primaryStage;
    private ShoppingCartHandler cartHandler = new ShoppingCartHandler(); // Shared cart instance

    /**
     * Initializes the scene manager with the primary application window.
     *
     * Requires: stage is not null.
     * Modifies: this
     * Effects: Stores the reference to the main stage for scene switching.
     *
     * @param stage The main stage of the JavaFX application.
     */
    public SceneManager(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Shows the login screen.
     *
     * Requires: loginScene class exists and is functional.
     * Modifies: primaryStage
     * Effects: Switches the current scene to the login screen.
     */
    public void showLoginScene() {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new LoginScene(this), width, height));
    }

    /**
     * Shows the main dashboard for the bookstore owner.
     */
    public void showOwnerScene() {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new OwnerScene(this), width, height));
    }

    /**
     * Shows the main dashboard for a logged-in customer.
     *
     * @param username The customer?s username.
     */
    public void showCustomerScene(String username) {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new CustomerScene(this, username), width, height));
    }

    /**
     * Displays the screen where the customer can view and manage their shopping cart.
     *
     * @param username The customer?s username.
     */
    public void showShoppingCartScene(String username) {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new ShoppingCartScene(this, username, cartHandler), width, height));
    }

    /**
     * Displays the checkout screen where the customer can see the final cost and make payment.
     *
     * @param username The customer?s username.
     */
    public void showCustomerCostScene(String username) {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new CustomerCostScene(this, username, cartHandler), width, height));
    }

    /**
     * Displays the screen for the owner to manage customer accounts.
     */
    public void showOwnerUserManageScene() {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new OwnerUserManageScene(this), width, height));
    }

    /**
     * Displays the screen for the owner to manage books in the store.
     */
    public void showOwnerBookManageScene() {
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();
        primaryStage.setScene(new Scene(new OwnerBookManageScene(this), width, height));
    }
}
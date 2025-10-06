package BookStore.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Customer dashboard where they can browse books and access the shopping cart.
 *
 * Abstraction Function:
 * - This scene serves as the main menu for a logged-in customer.
 * - Allows navigation to view books or log out of the application.
 *
 * Representation Invariant:
 * - sceneManager must be initialized.
 * - username must represent a valid customer in the system.
 */
public class CustomerScene extends VBox {
    private SceneManager sceneManager;
    private String username;

    /**
     * Constructs the customer dashboard screen.
     *
     * Requires: sceneManager is not null, username is valid.
     * Modifies: none
     * Effects:
     * - Displays a welcome message with the user's name.
     * - Provides buttons to view books or log out.
     *
     * @param sceneManager Manages navigation between screens.
     * @param username The logged-in customer's username.
     */
    public CustomerScene(SceneManager sceneManager, String username) {
        this.sceneManager = sceneManager;
        this.username = username;

        // Welcome label
        Label titleLabel = new Label("Welcome " + username);

        // Button to view available books and access the shopping cart
        Button viewBooksButton = new Button("View Books");

        // Button to return to the login screen
        Button logoutButton = new Button("Logout");

        // Event: Navigate to shopping cart scene when user clicks "View Books"
        viewBooksButton.setOnAction(e -> sceneManager.showShoppingCartScene(username));

        // Event: Log out and go back to the login screen
        logoutButton.setOnAction(e -> sceneManager.showLoginScene());

        // Layout styling
        setAlignment(Pos.CENTER);  // Center all elements
        setSpacing(10);            // Add spacing between components

        // Add all elements to the vertical layout
        getChildren().addAll(titleLabel, viewBooksButton, logoutButton);
    }
}

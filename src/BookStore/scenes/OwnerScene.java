package BookStore.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Owner dashboard for managing books and users.
 *
 * Abstraction Function:
 * - This scene serves as the main menu for the bookstore owner.
 * - Provides options to manage books, customers, or log out.
 *
 * Representation Invariant:
 * - sceneManager must be initialized and valid.
 */
public class OwnerScene extends VBox {
    private SceneManager sceneManager;

    /**
     * Constructs the owner dashboard with navigation buttons.
     *
     * Requires: sceneManager is not null.
     * Modifies: Active scene view.
     * Effects:
     * - Displays a layout with navigation options for the owner.
     * - Allows switching to book management, customer management, or logout.
     *
     * @param sceneManager Manages switching between scenes.
     */
    public OwnerScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        // Title label for the dashboard
        Label titleLabel = new Label("Owner Dashboard");

        // Navigation buttons
        Button booksButton = new Button("Manage Books");       // Takes owner to book management screen
        Button customersButton = new Button("Manage Customers"); // Takes owner to customer management screen
        Button logoutButton = new Button("Logout");            // Returns to login screen

        // Event: Go to book management screen
        booksButton.setOnAction(e -> sceneManager.showOwnerBookManageScene());

        // Event: Go to customer/user management screen
        customersButton.setOnAction(e -> sceneManager.showOwnerUserManageScene());

        // Event: Log out and return to login screen
        logoutButton.setOnAction(e -> sceneManager.showLoginScene());

        // Layout settings
        setAlignment(Pos.CENTER);  // Center all UI components
        setSpacing(10);            // Space between each button

        // Add all elements to the vertical layout
        getChildren().addAll(titleLabel, booksButton, customersButton, logoutButton);
    }
}

package BookStore.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.application.Platform;
import BookStore.handlers.LoginHandler;

/**
 * Login screen where users (Admin or Customer) enter their credentials.
 *
 * Abstraction Function:
 * - Allows users to log in as either an owner (admin) or customer.
 * - Verifies credentials and redirects to the appropriate dashboard.
 *
 * Representation Invariant:
 * - sceneManager must be initialized.
 */
public class LoginScene extends VBox {
    private SceneManager sceneManager;

    /**
     * Constructs the login screen layout and functionality.
     *
     * Requires: sceneManager is not null.
     * Modifies: Scene view.
     * Effects:
     * - Displays text fields and buttons for logging in or exiting.
     * - Handles user authentication and navigation.
     *
     * @param sceneManager Responsible for switching between scenes.
     */
    public LoginScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        // UI elements
        Label titleLabel = new Label("Welcome to the BookStore App");
        Label userLabel = new Label("Username:");
        Label passLabel = new Label("Password:");
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button exitButton = new Button("Exit");  // Closes the application

        /**
         * Login logic triggered when "Login" button is clicked.
         * Checks credentials using loginHandler and navigates to the appropriate dashboard.
         * Shows error alert if login fails.
         */
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            LoginHandler loginHandler = new LoginHandler();
            String role = loginHandler.validateUser(username, password);

            if (role.equals("owner")) {
                sceneManager.showOwnerScene(); // Navigate to owner dashboard
            } else if (role.equals("customer")) {
                sceneManager.showCustomerScene(username); // Navigate to customer dashboard
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid credentials");
                alert.showAndWait();  // Updated to block until dismissed
            }
        });

        /**
         * Exit logic triggered when "Exit" button is clicked.
         * Closes the application safely.
         */
        exitButton.setOnAction(e -> Platform.exit());

        // Login form layout using GridPane for better alignment
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(20));
        form.add(userLabel, 0, 0);
        form.add(usernameField, 1, 0);
        form.add(passLabel, 0, 1);
        form.add(passwordField, 1, 1);
        form.add(loginButton, 1, 2);
        form.add(exitButton, 1, 3);

        // Layout styling
        setAlignment(Pos.CENTER);  // Center all UI elements
        setSpacing(20);            // Add space between components
        getChildren().addAll(titleLabel, form);
    }
}
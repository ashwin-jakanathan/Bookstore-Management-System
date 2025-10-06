package BookStore.scenes;

import BookStore.handlers.OwnerHandler;
import BookStore.utils.InputValidator;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Scene where the owner can manage users: add, remove, and update balances.
 *
 * Abstraction Function:
 * - Provides a form for the bookstore owner to manage customer accounts.
 * - Allows adding new customers, removing them, or updating their balance.
 *
 * Representation Invariant:
 * - sceneManager and ownerHandler must be initialized.
 * - User input must be validated before applying any changes.
 */
public class OwnerUserManageScene extends VBox {
    private SceneManager sceneManager;
    private OwnerHandler ownerHandler;

    /**
     * Constructs the user management scene for the owner.
     *
     * Requires: sceneManager is not null.
     * Modifies: Customer list in the system.
     * Effects:
     * - Displays input fields and buttons for managing users.
     * - Shows alerts and error messages based on input.
     *
     * @param sceneManager Used to navigate between scenes.
     */
    public OwnerUserManageScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.ownerHandler = new OwnerHandler();

        // UI Elements
        Label title = new Label("Manage Users");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        TextField balanceField = new TextField();
        balanceField.setPromptText("Balance");

        Button addUserBtn = new Button("Add User");
        Button removeUserBtn = new Button("Remove User");
        Button updateBalanceBtn = new Button("Update Balance");
        Button backBtn = new Button("Back");

        /**
         * Handles adding a new user.
         * Validates balance input and checks if username already exists.
         */
        addUserBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String balanceText = balanceField.getText();

            if (!InputValidator.isNumeric(balanceText)) {
                showError("Invalid balance value.");
                return;
            }

            double balance = Double.parseDouble(balanceText);
            String result = ownerHandler.addCustomer(username, password, balance);

            if (result.contains("successfully")) {
                showAlert(result);
            } else {
                showError(result);
            }
        });

        /**
         * Handles removing a user by username.
         */
        removeUserBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String result = ownerHandler.removeCustomer(username);

            if (result.contains("successfully")) {
                showAlert(result);
            } else {
                showError(result);
            }
        });

        /**
         * Handles updating a user's balance.
         * Validates that the new balance is numeric.
         */
        updateBalanceBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String balanceText = balanceField.getText();

            double balance = Double.parseDouble(balanceText);
            String result = ownerHandler.adjustCustomerBalance(username, balance);
            if (result.contains("successfully")) {
                showAlert(result);
            } else {
                showError(result);
            }
            
        });

        // Go back to the owner's main dashboard
        backBtn.setOnAction(e -> sceneManager.showOwnerScene());

        // Layout styling
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(
                title, usernameField, passwordField, balanceField,
                addUserBtn, removeUserBtn, updateBalanceBtn, backBtn
        );
    }

    /**
     * Shows a popup message with informational feedback.
     *
     * @param msg The message to display.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.show();
    }

    /**
     * Shows a popup message for displaying an error.
     *
     * @param msg The error message to display.
     */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.show();
    }
}

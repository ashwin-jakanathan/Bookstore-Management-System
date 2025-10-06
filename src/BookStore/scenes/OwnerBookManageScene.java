package BookStore.scenes;

import BookStore.handlers.OwnerHandler;
import BookStore.utils.InputValidator;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * Scene where the owner can manage books: add and remove.
 *
 * Abstraction Function:
 * - Allows the bookstore owner to add new books or remove existing ones.
 * - Validates inputs and shows appropriate alerts for success or errors.
 *
 * Representation Invariant:
 * - sceneManager and ownerHandler must be initialized.
 * - UI layout must remain vertically aligned and user-friendly.
 */
public class OwnerBookManageScene extends VBox {
    private SceneManager sceneManager;
    private OwnerHandler ownerHandler;

    /**
     * Constructs the book management scene for the owner.
     *
     * Requires: sceneManager is not null.
     * Modifies: Book list in the system (via ownerHandler).
     * Effects:
     * - Displays UI for adding/removing books.
     * - Validates input and shows messages accordingly.
     *
     * @param sceneManager Used to navigate between scenes.
     */
    public OwnerBookManageScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.ownerHandler = new OwnerHandler();

        // UI components
        Label title = new Label("Manage Books");

        TextField titleField = new TextField();
        titleField.setPromptText("Book Title");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        Button addBookBtn = new Button("Add Book");
        Button removeBookBtn = new Button("Remove Book");
        Button backBtn = new Button("Back");

        /**
         * Handles adding a new book.
         * - Validates numeric input.
         * - Ensures price is one of the allowed values (50, 100, 200, 500).
         * - Displays success or error messages.
         */
        addBookBtn.setOnAction(e -> {
            String bookTitle = titleField.getText();
            String priceText = priceField.getText();

            // Ensure the price is numeric
            if (!InputValidator.isNumeric(priceText)) {
                showError("Please enter a valid number for the price.");
                return;
            }

            double price = Double.parseDouble(priceText);

            // Ensure price is one of the allowed fixed values
            if (!InputValidator.isValidBookPrice(price)) {
                showError("Price must be exactly 50, 100, 200, or 500.");
                return;
            }

            // Try to add the book using the handler
            String result = ownerHandler.addBook(bookTitle, price);
            if (result.contains("successfully")) {
                showAlert(result);
            } else {
                showError(result);
            }
        });

        /**
         * Handles removing a book by its title.
         * Always shows a success message for simplicity.
         */
        removeBookBtn.setOnAction(e -> {
            String bookTitle = titleField.getText();
            String result = ownerHandler.removeBook(bookTitle);
            if (result.contains("successfully")) {
                showAlert(result);
            } else {
                showError(result);
            }
        });

        // Go back to the owner's main scene
        backBtn.setOnAction(e -> sceneManager.showOwnerScene());

        // Layout configuration
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(
                title, titleField, priceField,
                addBookBtn, removeBookBtn, backBtn
        );
    }

    /**
     * Displays a popup alert with an information message.
     *
     * @param msg The message to show.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.show();
    }

    /**
     * Displays a popup alert with an error message.
     *
     * @param msg The message to show.
     */
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.show();
    }
}

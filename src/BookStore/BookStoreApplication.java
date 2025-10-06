package BookStore;

import javafx.application.Application;
import javafx.stage.Stage;
import BookStore.scenes.SceneManager;

/**
 * This is the main class that launches the Bookstore application.
 * It opens the login screen first when the program starts.
 *
 * Abstraction Function:
 * - Represents the main entry point of the bookstore program.
 * - Uses JavaFX to create a window and show the login interface.
 *
 * Representation Invariant:
 * - The window (Stage) should always show one of the scenes managed by sceneManager.
 * - sceneManager must not be null.
 */
public class BookStoreApplication extends Application {

    /**
     * Starts the JavaFX application.
     *
     * Requires: A valid Stage is provided by the JavaFX runtime.
     * Modifies: The primaryStage by setting its title and showing the login screen.
     * Effects: Creates a window with the title "Book Store" and displays the login page.
     */
    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showLoginScene(); // Start with login screen
        primaryStage.setTitle("Book Store");
        primaryStage.setMinWidth(800);      
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * Requires: No arguments required.
     * Modifies: Nothing directly.
     * Effects: Calls the JavaFX launch method, which then runs the start() method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

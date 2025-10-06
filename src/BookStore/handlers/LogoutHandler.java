package BookStore.handlers;

import BookStore.scenes.SceneManager;

/**
 * Handles user logout and redirects to the login screen.
 *
 * Abstraction Function:
 * - When a user chooses to log out, this class switches the current scene back to the login page.
 *
 * Representation Invariant:
 * - sceneManager must be properly initialized and should not be null.
 */
public class LogoutHandler {
    private SceneManager sceneManager;

    /**
     * Creates a logout handler that manages scene transitions.
     *
     * Requires: sceneManager is not null.
     * Modifies: this
     * Effects: Stores the reference to sceneManager for later use.
     */
    public LogoutHandler(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Logs out the current user and sends them back to the login screen.
     *
     * Requires: sceneManager is initialized.
     * Modifies: active scene
     * Effects: Changes the current screen to the login screen.
     */
    public void handleLogout() {
        sceneManager.showLoginScene();
    }
}

package BookStore.models;

/**
 * Abstract base class representing a user in the system.
 * This class is extended by specific user types like customers or owners.
 *
 * Abstraction Function:
 * - Represents a general user with a username and password.
 * - Used as a base class so that all types of users can share common information.
 *
 * Representation Invariant:
 * - username and password should not be null.
 * - username should be unique in the system.
 */
public abstract class User {
    protected String username;
    protected String password;

    /**
     * Creates a user with a given username and password.
     *
     * Requires: username and password are not null.
     * Modifies: this
     * Effects: Initializes the user with the provided username and password.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the username stored in this user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the password stored in this user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the password of the user.
     *
     * Requires: password is not null.
     * Modifies: this
     * Effects: Changes the user's password to the new value.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Converts the user object into a string.
     * This must be implemented by any subclass (like customer or owner).
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns a string that represents the user (based on user type).
     */
    public abstract String toString();
}

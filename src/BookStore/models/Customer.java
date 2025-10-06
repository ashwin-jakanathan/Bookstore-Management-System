package BookStore.models;

import BookStore.state.CustomerState;
import BookStore.state.SilverState;

/**
 * Represents a customer in the bookstore.
 * A customer has a balance (money), points, and a status (None, Silver, or Gold).
 *
 * Abstraction Function:
 * - A customer is a type of user who can buy books, earn points, and redeem points.
 * - The customer's balance is used to make purchases.
 * - The customer's points and status change depending on how much they spend.
 *
 * Representation Invariant:
 * - balance should be ≥ 0
 * - points should be ≥ 0
 * - state should never be null
 */
public class Customer extends User {
    private double balance;
    private int points;
    private CustomerState state;

    /**
     * Creates a customer with a username, password, and starting balance.
     *
     * Requires: username and password are not null, balance ≥ 0
     * Modifies: this
     * Effects: Initializes a new customer with default points (0) and Silver status.
     */
    public Customer(String username, String password, double balance) {
        super(username, password);
        this.balance = balance;
        this.points = 0;
        this.state = new SilverState(); // Default state is Silver
    }

    /**
     * Gets the current balance of the customer.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns how much money the customer has.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Updates the customer's balance.
     *
     * Requires: balance ≥ 0
     * Modifies: this
     * Effects: Sets a new balance amount for the customer.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Deducts a certain amount from the customer's balance.
     *
     * Requires: amount ≥ 0
     * Modifies: this
     * Effects: Subtracts amount from balance if the customer can afford it.
     * Returns true if successful, false otherwise.
     */
    public boolean deductBalance(double amount) {
        if (amount > balance) return false;
        this.balance -= amount;
        return true;
    }

    /**
     * Gets the number of points the customer has.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the customer's current points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds points to the customer's total and updates their status.
     *
     * Requires: points ≥ 0
     * Modifies: this
     * Effects: Increases the customer's points and updates their status if needed.
     */
    public void addPoints(int points) {
        this.points += points;
        state.updateStatus(this);
    }

    /**
     * Sets the customer’s total points directly.
     *
     * Requires: points ≥ 0
     * Modifies: this
     * Effects: Replaces the customer’s points with the new value and updates status.
     */
    public void setPoints(int points) {
        this.points = points;
        state.updateStatus(this);
    }

    /**
     * Redeems points to reduce the total cost of a purchase.
     * Every 100 points = $1 discount.
     *
     * Requires: cost ≥ 0
     * Modifies: this
     * Effects: Subtracts redeemable points and returns the discounted cost.
     */
    public double redeemPoints(double cost) {
        int redeemablePoints = (int) Math.min(points, cost * 100);
        double discount = redeemablePoints / 100.0;
        points -= redeemablePoints;
        return cost - discount;
    }

    /**
     * Updates the customer's current status (None, Silver, Gold).
     *
     * Requires: state is not null
     * Modifies: this
     * Effects: Changes the customer's state to the given value.
     */
    public void setState(CustomerState state) {
        this.state = state;
    }

    /**
     * Gets the customer's current status as a string.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the name of the customer’s current status (e.g., "Silver").
     */
    public String getStatus() {
        return state.getClass().getSimpleName();
    }

    /**
     * Returns a string summary of the customer’s info.
     *
     * Requires: none
     * Modifies: none
     * Effects: Displays the username, balance, points, and status.
     */
    @Override
    public String toString() {
        return username + " | Balance: $" + balance + " | Points: " + points + " | Status: " + getStatus();
    }
}

package BookStore.handlers;

import BookStore.database.DatabaseManager;
import BookStore.models.Customer;

import java.util.Map;

/**
 * Handles payment processing for customers.
 *
 * Abstraction Function:
 * - Processes book purchases using either customer balance or a combination of points and balance.
 * - Updates customer data accordingly after the purchase.
 *
 * Representation Invariant:
 * - databaseManager must be properly initialized.
 * - The customer must exist in the system before attempting a payment.
 */
public class PaymentHandler {
    private DatabaseManager dbManager = new DatabaseManager();

    /**
     * Processes the payment for a customer's cart.
     *
     * Requires:
     * - username must exist in the customer file.
     * - totalCost ≥ 0
     * - usePoints indicates whether to redeem points first before using balance.
     *
     * Modifies: customer’s points, balance, and stored file data.
     * Effects:
     * - If points fully cover the cost, deduct points only.
     * - If points partially cover the cost, deduct remaining cost from balance.
     * - If not enough points + balance, transaction fails.
     * - Awards 10 points per $1 spent from balance.
     *
     * @param username The customer's username.
     * @param totalCost The total amount to pay.
     * @param usePoints Whether to try using points before balance.
     * @return true if payment succeeds, false otherwise.
     */
    public boolean processPayment(String username, double totalCost, boolean usePoints) {
        Map<String, Customer> customers = dbManager.loadCustomers();
        Customer current = customers.get(username);

        if (usePoints) {
            int points = current.getPoints();
            double pointsValue = points / 10.0; // 10 points = $1
            double remainingCost = totalCost - pointsValue;

            // Case 1: Points fully cover the cost
            if (remainingCost <= 0) {
                current.setPoints(points - (int) (totalCost * 10));
                dbManager.saveCustomers(customers);
                return true;
            }

            // Case 2: Points partially cover cost, balance covers rest
            if (current.getBalance() >= remainingCost) {
                // Deduct all points
                current.setPoints(0);

                // Deduct from balance
                current.setBalance(current.getBalance() - remainingCost);

                // Earn points only on the balance portion
                int earned = (int) (remainingCost * 10);
                current.addPoints(earned);

                dbManager.saveCustomers(customers);
                return true;
            }

            // Case 3: Not enough points + balance
            return false;
        } else {
            
            // 💵 Case 4: Pay using only balance (no points)
            if (current.getBalance() >= totalCost) {
                current.setBalance(current.getBalance() - totalCost);

                // Award 10 points per $1 spent
                int earnedPoints = (int) (totalCost * 10);
                current.addPoints(earnedPoints);

                dbManager.saveCustomers(customers);
                return true;
            } else {
                return false;
            }
        }
    }
}

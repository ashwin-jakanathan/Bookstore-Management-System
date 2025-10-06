package BookStore.handlers;

import BookStore.models.Customer;
import BookStore.database.DatabaseManager;

/**
 * Handles customer-related actions such as book purchases.
 *
 * Abstraction Function:
 * - This class is responsible for managing customer purchases.
 * - It updates the customer's balance, applies points if needed, and stores the new data.
 *
 * Representation Invariant:
 * - dbManager must be initialized before calling any methods.
 * - The customer must exist in the database.
 */
public class CustomerHandler {
    private DatabaseManager dbManager = new DatabaseManager();

    /**
     * Handles the logic for a customer purchasing books.
     *
     * Requires: username exists in the customer file, totalCost ≥ 0
     * Modifies: customer's balance and points
     * Effects:
     * - If usePoints is true, it uses points first to reduce the cost.
     * - Then tries to deduct the remaining cost from the customer's balance.
     * - If the payment is successful, the customer earns points based on the cash amount spent.
     * - Customer data is updated in the file.
     * - Returns true if the purchase is successful, false otherwise.
     *
     * @param username The username of the customer making the purchase.
     * @param totalCost The full price of the books before any discounts.
     * @param usePoints Whether to redeem points toward the purchase.
     * @return true if the purchase is completed, false if not enough funds.
     */
    public boolean purchaseBooks(String username, double totalCost, boolean usePoints) {
        Customer customer = dbManager.getCustomer(username);

        if (usePoints) {
            // Try using points first to reduce the cost
            totalCost = customer.redeemPoints(totalCost);
        }

        if (customer.deductBalance(totalCost)) {
            // Earn 10 points for every dollar spent using cash
            customer.addPoints((int) (totalCost * 10));
            dbManager.updateCustomer(customer);
            return true;
        }

        return false; // Not enough money to complete the purchase
    }
}

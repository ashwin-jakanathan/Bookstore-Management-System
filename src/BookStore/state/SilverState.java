package BookStore.state;

import BookStore.models.Customer;

/**
 * Represents the Silver state of a customer.
 * Customers are in Silver state when they have less than 1000 points.
 *
 * Abstraction Function:
 * - A silverState means the customer has fewer than 1000 points.
 * - This class defines when the customer should be upgraded to Gold status.
 *
 * Representation Invariant:
 * - Customers in Silver state must have fewer than 1000 points.
 */
public class SilverState implements CustomerState {

    /**
     * Checks if the customer qualifies for Gold status.
     * If the customer's points reach 1000 or more, their status changes to Gold.
     *
     * Requires: customer is not null.
     * Modifies: customer's state
     * Effects: Upgrades the customer's status to Gold if points ≥ 1000.
     */
    @Override
    public void updateStatus(Customer customer) {
        if (customer.getPoints() >= 1000) {
            customer.setState(new GoldState());  // Upgrade to Gold
        }
    }

    /**
     * Returns the name of this customer status.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the string "Silver".
     */
    @Override
    public String toString() {
        return "Silver";
    }
}

package BookStore.state;

import BookStore.models.Customer;

/**
 * Represents the Gold state of a customer.
 * Customers reach Gold status when they have 1000 or more points.
 *
 * Abstraction Function:
 * - A goldState means the customer has earned at least 1000 points.
 * - This class defines how the customer can be downgraded back to Silver if needed.
 *
 * Representation Invariant:
 * - Customers in Gold state must have at least 1000 points.
 */
public class GoldState implements CustomerState {

    /**
     * Checks if the customer still qualifies for Gold status.
     * If the customer's points drop below 1000, their status changes to Silver.
     *
     * Requires: customer is not null.
     * Modifies: customer's state
     * Effects: Downgrades the customer's status to Silver if points < 1000.
     */
    @Override
    public void updateStatus(Customer customer) {
        if (customer.getPoints() < 1000) {
            customer.setState(new SilverState());  // Downgrade to Silver
        }
    }

    /**
     * Returns the name of this customer status.
     *
     * Requires: none
     * Modifies: none
     * Effects: Returns the string "Gold".
     */
    @Override
    public String toString() {
        return "Gold";
    }
}

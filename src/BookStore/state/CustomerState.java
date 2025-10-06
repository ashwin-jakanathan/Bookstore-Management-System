package BookStore.state;

import BookStore.models.Customer;

/**
 * Represents the state of a customer (like Silver or Gold).
 * This interface is part of the State Design Pattern used to manage customer status.
 *
 * Abstraction Function:
 * - A customer state defines how to update a customer's status based on their points.
 *
 * Representation Invariant:
 * - Implementing classes must define rules for upgrading or downgrading the customer's status.
 */
public interface CustomerState {

    /**
     * Updates the customer's status (e.g., Silver or Gold) based on their current points.
     *
     * Requires: customer is not null.
     * Modifies: customer's state
     * Effects: Changes the customer's state if their point total meets the criteria for a different status.
     */
    void updateStatus(Customer customer);
}

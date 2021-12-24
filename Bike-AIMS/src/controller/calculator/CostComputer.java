package controller.calculator;

import entity.Bike;
import entity.Rent;

import java.sql.Timestamp;

/**
 * Interface provides methods related cost computation.
 */
public interface CostComputer {

    /**
     *
     * @param rent bike that was rented by user
     * @param endTime the time user checking out
     * @return amount of money
     */
    float checkout(Rent rent, Timestamp endTime);

    /**
     *
     * @param bike
     * @return
     */
    float getDebit(Bike bike);

}

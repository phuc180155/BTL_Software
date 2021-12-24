package controller;

import checkout.CreditCard;
import checkout.InterbankInterface;
import checkout.InterbankSubsystem;
import checkout.PaymentTransaction;
import checkout.exception.PaymentException;
import entity.Bike;
import entity.Dock;
import entity.Rent;
import accessor.*;
import controller.calculator.CostComputer;
import controller.calculator.SimpleCostCalculator;
import entity.TransactionHistory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This class controls flow of system when user returns bike.
 */
public class ReturnBikeController extends BaseController{

    /**
     * String returned when user rent bike successfully.
     */
    private String SUCCESS_REFUND = "You returned bike successfully!";

    private CostComputer calculator;
    private RentAccessor rentAccessor;
    private BikeAccessor bikeAccessor;
    private DockAccessor dockAccessor;
    private InterbankInterface interbank;
    private TransactionHistoryAccessor transactionHistoryAccessor;

    public ReturnBikeController() {
        this.calculator = new SimpleCostCalculator();
        this.bikeAccessor = new BikeAccessor();
        this.rentAccessor = new RentAccessor();
        this.dockAccessor = new DockAccessor();
        this.interbank = new InterbankSubsystem();
        this.transactionHistoryAccessor = new TransactionHistoryAccessor();
    }

    /**
     * calculate cost of bike rented to specific time
     * @param rent bike rented by user
     * @param endTime the time that user rent bike to
     * @return
     */
    public float calculateCost(Rent rent, Timestamp endTime) {
        return this.calculator.checkout(rent, endTime);
    }

    /**
     * return return bike rented by user
     * @param userId id of user
     * @param rent user's rented bike
     * @param creditCard credit card info
     * @param endTime time that user returns bike
     * @return
     */
    public String requestReturnBike(int userId, Rent rent, CreditCard creditCard, Timestamp endTime) {
        if (endTime == null) {
            endTime = new Timestamp((new Date()).getTime());
        }
        int debit = (int) rent.getDebit();
        int cost = (int) calculateCost(rent, endTime);
        int returnMoney = debit - cost;
        Bike bike = rent.getRentedBike();
        try {
            PaymentTransaction paymentTransaction = this.interbank.refund(creditCard, returnMoney, "return bike " + bike.getBikeName());
            // Update bike status:
            bike.setStatus(false);
            this.bikeAccessor.update(bike);
            // Increase dock availableBikes:
            Dock dock = this.dockAccessor.get(bike.getDockId());
            int curAvailableBikes = dock.getAvailableBikes();
            dock.setAvailableBikes(curAvailableBikes+1);
            this.dockAccessor.update(dock);
            // Update rent:
            rent.setEndTime(endTime);
            this.rentAccessor.update(rent);
            // Make transaction
            TransactionHistory transactionHistory = makeTransactionHistory(userId, rent, paymentTransaction);
            transactionHistoryAccessor.save(transactionHistory);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "Error while return bike!";
        } catch (PaymentException e) {
            return e.getMessage();
        }
        return SUCCESS_REFUND;
    }

    private TransactionHistory makeTransactionHistory(int userId, Rent rent, PaymentTransaction paymentTransaction) {
        int transactionId = -1;
        int bikeId = rent.getRentedBike().getBikeId();
        float totalCost = (float) paymentTransaction.getAmount();
        String content = paymentTransaction.getTransactionContent();
        Timestamp createAt = new Timestamp(paymentTransaction.getCreateAt().getTime());

        long duration = rent.getEndTime().getTime() - rent.getStartTime().getTime();
        long rentMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        return new TransactionHistory(-1, userId, bikeId, totalCost,content, (float) (rentMinutes / 60.0), createAt);
    }
}

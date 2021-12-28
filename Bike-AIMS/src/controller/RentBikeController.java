package controller;

import barcode.BarcodeProcessor;
import checkout.CreditCard;
import checkout.InterbankInterface;
import checkout.InterbankSubsystem;
import checkout.PaymentTransaction;
import checkout.exception.PaymentException;
import entity.Bike;
import controller.calculator.CostComputer;
import controller.calculator.SimpleCostCalculator;
import accessor.*;
import entity.Dock;
import entity.Rent;
import utils.Configs;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.sql.Timestamp;

/**
 * This class controls flow of system when user rents bike.
 */
public class RentBikeController extends BaseController {

    private BarcodeProcessor barcodeProcessor;
    private BikeAccessor bikeAccessor;
    private RentAccessor rentAccessor;
    private DockAccessor dockAccessor;
    private InterbankInterface interbank;
    private CostComputer costComputer;

    public RentBikeController() {
        this.barcodeProcessor = new BarcodeProcessor();
        this.bikeAccessor = new BikeAccessor();
        this.dockAccessor = new DockAccessor();
        this.rentAccessor = new RentAccessor();
        this.interbank = new InterbankSubsystem();
        this.costComputer = new SimpleCostCalculator();
    }

    /**
     * get bike for user
     * @param barcode barcode in the bike
     * @return bike matched with barcode
     */
    public Bike requestBike(String barcode) {
        int bikeId = this.barcodeProcessor.processBarcode(barcode);
        return bikeAccessor.get(bikeId);
    }

    /**
     * Request rent bike for user
     * @param userId id of user
     * @param bike the bike wanted to rent by user
     * @param creditCard credit card of user
     * @return notification string
     */
    public String requestRentBike(int userId, Bike bike, CreditCard creditCard) {
        if (bike.isStatus()){
            return Configs.BIKE_IS_RENTED;
        }
        int cost = (int) this.costComputer.getDebit(bike);
        try {
            PaymentTransaction paymentTransaction = this.interbank.payRental(creditCard, cost, "rent bike" + bike.getBikeName());
            Date date  = new Date();
            Timestamp startTime = new Timestamp(date.getTime());
            int rentId = -1;
            // Decrease available bikes of dock:
            Dock dock = this.dockAccessor.get(bike.getDockId());
            int curAvailableBikes = dock.getAvailableBikes();
            dock.setAvailableBikes(curAvailableBikes-1);
            this.dockAccessor.update(dock);
            // Set bike is rented:
            bike.setStatus(true);
            this.bikeAccessor.update(bike);

            // Save rent:
            Rent rent = new Rent(-1, userId, bike, cost, startTime, null);
            this.rentAccessor.save(rent);
            return Configs.SUCCESS_NOTIFICATION;

        } catch (PaymentException e) {
            return e.getMessage();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "Cannot process transaction!";
    }
}

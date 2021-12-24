package controller.calculator;

import entity.Bike;
import entity.Rent;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class SimpleCostCalculator implements CostComputer {

    @Override
    public float checkout(Rent rent, Timestamp endTime) {
        float initCost = rent.getRentedBike().getInitCost();
        float costPerQuarterHour = rent.getRentedBike().getCostPerQuarterHour();
        float ratio = rent.getRentedBike().getCategory().getCostRatio();
        float cost = initCost;

        long minutes = getMinutes(rent.getStartTime(), endTime);
        if (minutes > 30){
            float extraTime = minutes - 30;
            float extraCost = (Math.round(extraTime/15.0f) + 1) * costPerQuarterHour;
            cost += extraCost;
        }
        return cost;
    }

    protected long getMinutes(Timestamp start, Timestamp end) {
        long duration = (end.getTime() - start.getTime());
        return TimeUnit.MILLISECONDS.toMinutes(duration);
    }

    @Override
    public float getDebit(Bike bike)  {
        return bike.getCategory().getBikeValue();
    }
}

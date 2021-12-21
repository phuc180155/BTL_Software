package entity;

import java.sql.Timestamp;

public class Rent {
    private int rentId;
    private int userId;
    private Bike rentedBike;
    private int debit;
    private Timestamp startTime;
    private Timestamp endTime;

    public Rent(int rentId, int userId, Bike rentedBike, int debit, Timestamp startTime, Timestamp endTime) {
        this.rentId = rentId;
        this.userId = userId;
        this.rentedBike = rentedBike;
        this.debit = debit;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getRentId() {
        return rentId;
    }

    public int getUserId() {
        return userId;
    }

    public Bike getRentedBike() {
        return rentedBike;
    }

    public int getDebit() {
        return debit;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRentedBike(Bike rentedBike) {
        this.rentedBike = rentedBike;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    //    public Rent(int bikeId, String bikeName, int dockId, float pin, boolean status,
//                Category category, int userId, int debit, Timestamp start_time) {
//        super(bikeId, bikeName, dockId, pin, status, category);
//        this.userId = userId;
//        this.debit = debit;
//        this.start_time = start_time;
//        this.end_time = null;
//        this.rentId = -1;
//    }

//    public Rent(int bikeId, String bikeName, int dockId, float pin,
//                boolean status, Category category, int userId, int debit, Timestamp start_time,
//                Timestamp end_time, int rentId ) {
//        super(bikeId, bikeName, dockId, pin, status, category);
//        this.userId = userId;
//        this.debit = debit;
//        this.start_time = start_time;
//        this.end_time = end_time;
//        this.rentId = rentId;
//    }

//    @Override
//    public String toString() {
//        return "Xe số: " + this.bikeId + " thuê ở bãi xe số " + this.dockId;
//    }
}

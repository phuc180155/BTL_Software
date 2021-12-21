package entity;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int userId;
    private Rent rent;
    private int bikeId;
    private float totalCost;
    private String content;
    private float rentedDuration;
    private Timestamp modifyAt;
    private String errorCode;

    public Transaction(int transactionId, int userId, Rent rent, int bikeId, float totalCost, String content, float rentedDuration, Timestamp modifyAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.rent = rent;
        this.bikeId = bikeId;
        this.totalCost = totalCost;
        this.content = content;
        this.rentedDuration = rentedDuration;
        this.modifyAt = modifyAt;
    }

    public Transaction(String errorCode){
        this.errorCode = errorCode;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRentedDuration(float rentedDuration) {
        this.rentedDuration = rentedDuration;
    }

    public void setModifyAt(Timestamp modifyAt) {
        this.modifyAt = modifyAt;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public Rent getRent() {
        return rent;
    }

    public int getBikeId() {
        return bikeId;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public String getContent() {
        return content;
    }

    public float getRentedDuration() {
        return rentedDuration;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Timestamp getModifyAt() {
        return modifyAt;
    }
    //    public Transaction(int transactionId, int userId, float total_payment, Timestamp time, String bike_name, float rented_duration) {
//        this.transactionId = transactionId;
//        this.userId = userId;
//        this.total_payment = total_payment;
//        this.time = time;
//        this.bike_name = bike_name;
//        this.rented_duration = rented_duration;
//    }

    //    @Override
//    public String toString() {
//        return "TransactionHistory{" +
//                "transactionId=" + this.transactionId +
//                ", userId=" + this.userId +
//                ", total_payment=" + this.total_payment +
//                ", time=" + this.time +
//                ", bike_name='" + this.bike_name + '\'' +
//                ", rented_duration=" + this.rented_duration +
//                '}';
//    }
}

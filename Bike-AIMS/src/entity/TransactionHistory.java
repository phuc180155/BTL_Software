package entity;

import java.sql.Timestamp;

public class TransactionHistory {
    private int transactionId;
    private int userId;
    private int bikeId;
    private float totalCost;
    private String content;
    private float rentedDuration;
    private Timestamp createAt;
    private String errorCode;

    public TransactionHistory(int transactionId, int userId, int bikeId, float totalCost, String content, float rentedDuration, Timestamp createAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bikeId = bikeId;
        this.totalCost = totalCost;
        this.content = content;
        this.rentedDuration = rentedDuration;
        this.createAt = createAt;
    }

    public TransactionHistory(String errorCode){
        this.errorCode = errorCode;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setModifyAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
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

    public Timestamp getCreateAt() {
        return createAt;
    }


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

package entity;

import java.util.List;

public class Dock {

    private int dockId;
    private String dockName;
    private String address;
    private float dockArea;
    private int availableBikes;
    private int emptyDockingPoints;
    private float distance;
    private float walkingTime;
    private String imagePath;

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public void setDockName(String dockName) {
        this.dockName = dockName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDockArea(float dockArea) {
        this.dockArea = dockArea;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }

    public void setEmptyDockingPoints(int emptyDockingPoints) {
        this.emptyDockingPoints = emptyDockingPoints;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setWalkingTime(float walkingTime) {
        this.walkingTime = walkingTime;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Dock(int dockId, String dockName, String address, float dockArea, int availableBikes, int emptyDockingPoints, float distance, float walkingTime, String imagePath) {
        this.dockId = dockId;
        this.dockName = dockName;
        this.address = address;
        this.dockArea = dockArea;
        this.availableBikes = availableBikes;
        this.emptyDockingPoints = emptyDockingPoints;
        this.distance = distance;
        this.walkingTime = walkingTime;
        this.imagePath = imagePath;
    }

    public int getDockId() {
        return dockId;
    }

    public String getDockName() {
        return dockName;
    }

    public String getAddress() {
        return address;
    }

    public float getDockArea() {
        return dockArea;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public int getEmptyDockingPoints() {
        return emptyDockingPoints;
    }

    public float getDistance() {
        return distance;
    }

    public float getWalkingTime() {
        return walkingTime;
    }

    public String getImagePath() {
        return imagePath;
    }
//    @Override
//    public String toString() {
//        return "Dock{" +
//                "dockId=" + this.dockId +
//                ", dockName='" + this.dockName + '\'' +
//                ", address='" + this.address + '\'' +
//                ", imagePath='" + this.imagePath + '\'' +
//                '}';
//    }
}

package entity;

public class Bike {

    protected int bikeId;
    protected String bikeName;
    protected String licensePlate;

    protected float pin;
    protected boolean status;
    protected float initCost;
    protected float costPerQuarterHour;

    protected int dockId;
    protected Category category;
    protected String imagePath;

    public Bike(int bikeId, String bikeName, String licensePlate, float pin, boolean status, float initCost, float costPerQuarterHour, int dockId, Category category, String imagePath) {
        this.bikeId = bikeId;
        this.bikeName = bikeName;
        this.licensePlate = licensePlate;
        this.pin = pin;
        this.status = status;
        this.initCost = initCost;
        this.costPerQuarterHour = costPerQuarterHour;
        this.dockId = dockId;
        this.category = category;
        this.imagePath = imagePath;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getBikeName() {
        return bikeName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public float getPin() {
        return pin;
    }

    public boolean isStatus() {
        return status;
    }

    public float getInitCost() {
        return initCost;
    }

    public float getCostPerQuarterHour() {
        return costPerQuarterHour;
    }

    public int getDockId() {
        return dockId;
    }

    public Category getCategory() {
        return category;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setPin(float pin) {
        this.pin = pin;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setInitCost(float initCost) {
        this.initCost = initCost;
    }

    public void setCostPerQuarterHour(float costPerQuarterHour) {
        this.costPerQuarterHour = costPerQuarterHour;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

//    @Override
//    public String toString() {
//        return "Xe đạp số " + this.bikeId + ".Tên xe: " +  this.bikeName + ". Barcode: " + this.bikeId;
//    }
}

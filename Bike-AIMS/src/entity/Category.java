package entity;

public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private float bikeValue;
    private float costRatio;

    public Category(int categoryId, String categoryName, String categoryDescription, float bikeValue, float costRatio) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.bikeValue = bikeValue;
        this.costRatio = costRatio;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public float getBikeValue() {
        return bikeValue;
    }

    public float getCostRatio() {
        return costRatio;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setBikeValue(float bikeValue) {
        this.bikeValue = bikeValue;
    }

    public void setCostRatio(float costRatio) {
        this.costRatio = costRatio;
    }

    //    public Category(int categoryId, String name, String description, float cost_per_hour, int n_pedals, int n_seats) {
//        this.categoryId = categoryId;
//        this.name = name;
//        this.description = description;
//        this.cost_per_hour = cost_per_hour;
//        this.n_pedals = n_pedals;
//        this.n_seats = n_seats;
//    }


//    @Override
//    public String toString() {
//        return "Category{" +
//                "categoryId=" + this.categoryId +
//                ", name='" + this.name + '\'' +
//                ", description='" + this.description + '\'' +
//                ", cost_per_hour=" + this.cost_per_hour +
//                ", n_pedals=" + this.n_pedals +
//                ", n_seats=" + this.n_seats +
//                '}';
//    }
}

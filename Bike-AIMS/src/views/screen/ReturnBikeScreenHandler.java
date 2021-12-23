package views.screen;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReturnBikeScreenHandler extends BaseScreenHandler{
    @FXML
    private Button backBtn;

    @FXML
    private ImageView logo, backIcon;

    @FXML
    private TextField cardHolderName, cardNumber, cvv, expirationDate;

    @FXML
    private Button confirmRentBikeBtn, validInformationBtn;

    @FXML
    private ImageView imageFoot11, imageFoot12, imageFoot13, imageFoot21, imageFoot22;

    @FXML
    private Label titleLabel;

    @FXML
    private ImageView bikeImage;

    @FXML
    private ListView<String> bikeInfoListView;

    private Rent rent;
    private Bike rentedBike;

    @FXML
    void goBackPreviousScreen(ActionEvent event) {
        this.getPreviousScreen().show();
    }


    public ReturnBikeScreenHandler(Stage stage, String screenPath, Rent rent) throws IOException {
        super(stage, screenPath);
        this.rent = rent;
        this.rentedBike = rent.getRentedBike();
    }

    public void initiate() {
        // Set image and title
        this.setImage(true);
        this.setSingleImage(bikeImage, rentedBike.getImagePath());
        titleLabel.setText("Return Payment Transaction");
        // rent data
        this.loadData();
    }

    public void loadData() {
        String bikeId = "Bike id: " + rentedBike.getBikeId();
        String bikeName = "Bike name:  " + rentedBike.getBikeName();
        String licensePlate = "License plate: " + rentedBike.getLicensePlate();
        String pin = (rentedBike.getCategory().getCategoryId() == 3) ? "Pin: " + Float.toString(rentedBike.getPin()) : null;
        String status =  rentedBike.isStatus() ? "Trạng thái: Đang cho mượn" : "Trạng thái: Sẵn sàng";
        String bikeCategory = "Caterory: " + rentedBike.getCategory().getCategoryName();
        String description = "Description: " + rentedBike.getCategory().getCategoryDescription();
        String costPerQuarterHour = "Cost per 15 minutes: " + convertCurrencyFormat(rentedBike.getCostPerQuarterHour()*1000);
        String bikeValue = "Bike value: " + convertCurrencyFormat(rentedBike.getCategory().getBikeValue()*1000) + " VNĐ";
        ObservableList<String> items = null;
        if (pin == null){
            items = FXCollections.observableArrayList(bikeId, bikeName, licensePlate, status, bikeCategory, description, costPerQuarterHour, bikeValue);
        } else {
            items = FXCollections.observableArrayList(bikeId, bikeName, licensePlate, status, pin, bikeCategory, description, costPerQuarterHour, bikeValue);
        }
        bikeInfoListView.setItems(items);
        bikeInfoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void resetDefaultValue(ActionEvent actionEvent) {
        /**
         * Handle event when user click on valid information button, fill in the default value of card
         */
        // Get the data from user(fix)
        cardNumber.setText("kstn_group8_2021");
        cardHolderName.setText("Group 8");
        cvv.setText("412");
        expirationDate.setText("1125");
    }
}

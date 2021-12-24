package views.screen;

import checkout.CreditCard;
import controller.RentBikeController;
import entity.Bike;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;

public class RentBikeScreenHandler extends BaseScreenHandler{
    @FXML
    private Button backBtn;

    @FXML
    private ImageView logo, backIcon;

    @FXML
    private TextField cardHolderName, cardNumber, cvv, expirationDate;

    @FXML
    private Button confirmBtn, validInformationBtn;

    @FXML
    private Label titleLabel;

    @FXML
    private ImageView bikeImage;

    @FXML
    private ListView<String> bikeInfoListView;

    private int userId;
    private Bike bike;

    @FXML
    void goBackPreviousScreen(ActionEvent event) {
        HomeScreenHandler homeScreenHandler = (HomeScreenHandler) this.getPreviousScreen();
        homeScreenHandler.initialize();
        homeScreenHandler.setScreenTitle("Home Screen");
        homeScreenHandler.show();
    }

    public RentBikeScreenHandler(Stage stage, String screenPath, int userId, Bike bike) throws IOException {
        super(stage, screenPath);
        this.userId = userId;
        this.bike = bike;
    }

    public void initiate() {
        // Set image and title
        this.setImage(true);
        this.setSingleImage(backIcon, Configs.IMAGE_PATH + "/backarrow.jpg");
        this.setSingleFitImage(bikeImage, bike.getImagePath(), 420, 250);
        titleLabel.setText("Rent Payment Transaction");
        confirmBtn.setText("Confirm rent bike");
        // rent data
        this.loadData();
    }

    public void loadData() {
        String bikeId = "Bike id: " + bike.getBikeId();
        String bikeName = "Bike name:  " + bike.getBikeName();
        String licensePlate = "License plate: " + bike.getLicensePlate();
        String pin = (bike.getCategory().getCategoryId() == 3) ? "Pin: " + Float.toString(bike.getPin()) : null;
        String status =  bike.isStatus() ? "Trạng thái: Đang cho mượn" : "Trạng thái: Có sẵn";
        String bikeCategory = "Category: " + bike.getCategory().getCategoryName();
        String description = "Description: " + bike.getCategory().getCategoryDescription();
        String startPrice = "Cost of the first 30 minutes: " + convertCurrencyFormat(bike.getInitCost()*1000) + " VNĐ";
        String costPerQuarterHour = "Cost per 15 minutes: " + convertCurrencyFormat(bike.getCostPerQuarterHour()*1000) + " VNĐ";

        ObservableList<String> items = FXCollections.observableArrayList(bikeId, bikeName, licensePlate, status, bikeCategory, description, startPrice, costPerQuarterHour);;
        if (pin != null){
            items.add(4, pin);
        }
        bikeInfoListView.setPadding(new Insets(0, 0, 0, 5));
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

    @FXML
    void confirm(ActionEvent event) throws IOException {
        /**
         * Handle event when user click on confirm rent bike event, go to the notification screen
         */
        // Get the data from user
        CreditCard creditCard = new CreditCard(cardNumber.getText(), cardHolderName.getText(),
                cvv.getText(), expirationDate.getText());

        // Get result of rent bike from api and go to notification screen
        RentBikeController rentBikeController = new RentBikeController();
        String result = rentBikeController.requestRentBike(this.userId, this.bike, creditCard);
        if (result.contains("success")){
            PopupScreen.success(result);
            return;
        }
        PopupScreen.error(result);
    }
}

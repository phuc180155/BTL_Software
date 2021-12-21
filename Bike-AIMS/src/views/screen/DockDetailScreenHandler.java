package views.screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DockDetailScreenHandler extends BaseScreenHandler{
    @FXML
    private Button backBtn;

    @FXML
    private TableView<?> bikeListTable;

    @FXML
    private TableColumn<?, ?> index, barcode, bikeId, bikeName, category, status, costPerQuarterHour, currentPIN, licensePlate;

    @FXML
    private ImageView logo, dockImage, backIcon;

    @FXML
    private Label dockNameLabel, titleLabel;

    @FXML
    private ImageView footImage11, footImage12, footImage13, footImage21, footImage22;


    @FXML
    public void goBackPreviousScreen(ActionEvent event) {

    }

    @FXML
    public void goBikeDetailScreen(MouseEvent event) {

    }

    private int stationID;

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public DockDetailScreenHandler(Stage stage, String screenPath, int stationID) throws IOException {
        super(stage, screenPath);
        this.stationID = stationID;

    }


    public void goBack(ActionEvent actionEvent) {
        /**
         * Handle event when user click back button, go back to home screen
         */

    }

    public void goViewBikeScreen(MouseEvent mouseEvent) throws IOException {
        /**
         * Handle event when user click on a bike in list bike of station, go to the detail bike information screen
         */
//        Bike bike = list.getSelectionModel().getSelectedItem();
//        App.getInstance().display_ViewBikeScreen(this.getStationID(), bike);
    }
}

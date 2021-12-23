package views.screen;

import controller.BikeController;
import controller.UserController;
import entity.Rent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;

import java.io.IOException;
import java.util.List;

;import static views.screen.home.HomeScreenHandler.LOGGER;

public class RentedBikeListScreenHandler extends BaseScreenHandler {

    private int userId;

    @FXML
    private Button backBtn;

    @FXML
    private ImageView logo, backIcon;

    @FXML
    private ImageView footImage11, footImage12, footImage13, footImage21, footImage22;

    @FXML
    private Label titleLabel;

    @FXML
    private ListView<Rent> rentedBikeListView;

    @FXML
    void goBackPreviousScreen(ActionEvent event) {
        this.getPreviousScreen().show();
    }

    public RentedBikeListScreenHandler(Stage stage, String screenPath, int userId) throws IOException {
        super(stage, screenPath);
        this.userId = userId;
    }

    public void initiate(){
        // Set image:
        this.setImage(true);
        // BackIcon:
        this.setSingleImage(backIcon,Configs.IMAGE_PATH + "/" +"capstone_backarrow.jpg");
        // Set title
        this.setScreenTitle("Bike Detail");
        // Set title label:
        titleLabel.setText("List Rented Bikes");
        System.out.println((RentedBikeListScreenHandler) this.loader.getController());
        this.loadData();
    }

    public void loadData(){
        //Rent Listbikerented took from user
        UserController userController = new UserController();
        List<Rent> rentLst = userController.getRentByUserId(this.userId);
        ObservableList<Rent> items = FXCollections.observableArrayList(rentLst);
        rentedBikeListView.setItems(items);

    }

    @FXML
    void goReturnBikeScreen(MouseEvent event) {
        Rent rent = rentedBikeListView.getSelectionModel().getSelectedItem();
        ReturnBikeScreenHandler returnBikeScreenHandler;
        try {
            LOGGER.info("User clicked to view return bike");
            returnBikeScreenHandler = new ReturnBikeScreenHandler(this.stage, Configs.CREDIT_CARD_FORM_PATH, rent);
            returnBikeScreenHandler.setHomeScreenHandler(this.homeScreenHandler);
//            returnBikeScreenHandler.setBController(new BikeController());
            returnBikeScreenHandler.setPreviousScreen(this);
            returnBikeScreenHandler.initiate();
            returnBikeScreenHandler.show();
        } catch (IOException e1){
            LOGGER.info("Errors occured: " + e1.getMessage());
            e1.printStackTrace();
        }
    }
}

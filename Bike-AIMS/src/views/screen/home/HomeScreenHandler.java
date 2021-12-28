package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import accessor.DockAccessor;
import controller.*;

import entity.Bike;
import entity.Dock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import utils.Configs;
import utils.Utils;
import views.screen.*;

public class HomeScreenHandler extends BaseScreenHandler{

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());
    public static final int userId = 1;

    @FXML
    private TextField barcodeTextField;

    @FXML
    private TableView<Dock> dockInfoTable;

    @FXML
    private TableColumn<Dock, String> dockName, address;

    @FXML
    private TableColumn<Dock, Integer> dockId, emptyDockingPoints, availableBikes;

    @FXML
    private TableColumn<Dock, Float> distance, dockArea, walkingTime;

    @FXML
    private Label numberRentedBikes;

    @FXML
    private ImageView logo, rentedBikeIcon;

    @FXML
    private Button rentBikeBtn, rentedBikeCartBtn;

    @FXML
    private SplitMenuButton searchDockBtn;

    @FXML
    private TextField searchDockTextField;

//    @FXML
//    private ImageView footImage11, footImage12, footImage13, footImage21, footImage22;


    private static final int rowsPerPage = 10;

    private static int searchOption = -1;


    public HomeScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
        setBController(new HomeController());
        setHomeScreenHandler(this);
    }

    @Override
    public void show(){
//        numMediaInCart.setText(String.valueOf(Cart.getCart().getListMedia().size()) + " media");
        super.show();
    }

    public void initialize() {
        try {
            this.setImage(true);

            setBController(new HomeController());
            HomeController homeController = (HomeController) this.bController;
            List<Dock> docks = homeController.getAllDock();
            // Thêm vào list của FXCollections
            this.loadDataToDockTable(docks);

            // Set Image:
            this.setSingleImage(rentedBikeIcon, Configs.IMAGE_PATH + "/rentedBikeCart.png");

            // Set number of current rented bikes:
            UserController userController = new UserController();
            numberRentedBikes.setText("" + userController.getRentByUserId(userId).size());

            // Search Dock
            ObservableList<MenuItem> menuItems = searchDockBtn.getItems();
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem menuItem = menuItems.get(i);
                int finalI = i;
                if (i == menuItems.size() - 1) {
                    menuItem.setOnAction((e) -> {
                        this.loadDataToDockTable(docks);
                    });
                } else {
                    int finalI1 = i;
                    menuItem.setOnAction((e) -> {
                        searchOption = finalI;
                    });
                }
            }

            searchDockBtn.setOnAction((e)->{
                System.out.println(searchOption);
                if (searchOption != -1) {
                    String info = searchDockTextField.getText();
                    List<Dock> dockLst = homeController.searchDock(searchOption, info);
                    this.loadDataToDockTable(dockLst);
                }
            });

            logo.setOnMouseClicked(e -> {
                this.homeScreenHandler.show();
            });

        } catch (SQLException e) {
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goDockDetailScreen(MouseEvent e) throws IOException {
        /**
         * Handle event when user click on dock, go to the dock detail screen of choosen dock
         */
        Dock dock = dockInfoTable.getSelectionModel().getSelectedItem();
        int id = dock.getDockId();
        DockDetailScreenHandler dockDetailScreen;
        try {
            LOGGER.info("User clicked to view a dock");
            dockDetailScreen = new DockDetailScreenHandler(this.stage, Configs.DOCK_DETAIL_SCREEN_PATH, id);
            dockDetailScreen.setHomeScreenHandler(this);
            dockDetailScreen.setBController(new DockController());
            dockDetailScreen.setScreenTitle("Dock Detail Screen");
            dockDetailScreen.setPreviousScreen(this);
            dockDetailScreen.initiate();
            dockDetailScreen.show();
        } catch (IOException e1){
            LOGGER.info("Errors occured: " + e1.getMessage());
            e1.printStackTrace();
        }
    }

    public void loadDataToDockTable(List<Dock> docks) {
        ObservableList<Dock> dockList = FXCollections.observableArrayList();
        dockList.addAll(docks);
        // Thêm thông tin dock vào bảng:
        dockId.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("dockId"));
        dockName.setCellValueFactory(new PropertyValueFactory<Dock, String>("dockName"));
        address.setCellValueFactory(new PropertyValueFactory<Dock, String>("address"));
        dockArea.setCellValueFactory(new PropertyValueFactory<Dock, Float>("dockArea"));
        availableBikes.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("availableBikes"));
        emptyDockingPoints.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("emptyDockingPoints"));
        distance.setCellValueFactory(new PropertyValueFactory<Dock, Float>("distance"));
        walkingTime.setCellValueFactory(new PropertyValueFactory<Dock, Float>("walkingTime"));
        dockInfoTable.setItems(dockList);
    }

    @FXML
    void goRentedBikeListScreen(ActionEvent event) {
        RentedBikeListScreenHandler rentedBikeListScreenHandler;
        try {
            LOGGER.info("User clicked to view a dock");
            rentedBikeListScreenHandler = new RentedBikeListScreenHandler(this.stage, Configs.RENTED_BIKE_LIST_SCREEN_PATH, userId);
            rentedBikeListScreenHandler.setHomeScreenHandler(this.homeScreenHandler);
            rentedBikeListScreenHandler.setPreviousScreen(this);
            rentedBikeListScreenHandler.setScreenTitle("Rented Bike List");
            rentedBikeListScreenHandler.initiate();
            rentedBikeListScreenHandler.show();
        } catch (IOException e1){
            LOGGER.info("Errors occured: " + e1.getMessage());
            e1.printStackTrace();
        }
    }


    @FXML
    void goRentBikeScreenHandler(ActionEvent event) throws IOException {
        // Get and barcode:
        String barcode = barcodeTextField.getText();
        BarcodeController barcodeController = new BarcodeController();
        boolean checkBarcode = barcodeController.validateBarcode(barcode);
        if (!checkBarcode){
            PopupScreen.error("Invalid Barcode!");
            return;
        }
        RentBikeScreenHandler rentBikeScreenHandler;
        RentBikeController rentBikeController = new RentBikeController();
        Bike bike = rentBikeController.requestBike(barcode);

        try {
            LOGGER.info("User clicked barcode");
            rentBikeScreenHandler = new RentBikeScreenHandler(this.stage, Configs.CREDIT_CARD_FORM_PATH, userId, bike);
            rentBikeScreenHandler.setHomeScreenHandler(this.homeScreenHandler);
            rentBikeScreenHandler.setScreenTitle("Rent Bike Screen");
            rentBikeScreenHandler.setPreviousScreen(this);
            rentBikeScreenHandler.setBController(new RentBikeController());
            rentBikeScreenHandler.initiate();
            rentBikeScreenHandler.show();
        } catch (IOException e1) {
            LOGGER.info("Errors occured: " + e1.getMessage());
            e1.printStackTrace();
        }

    }
}
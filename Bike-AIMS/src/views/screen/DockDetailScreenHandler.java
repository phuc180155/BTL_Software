package views.screen;

import com.mysql.jdbc.StringUtils;
import controller.BikeController;
import controller.DockController;
import controller.HomeController;
import entity.Bike;
import entity.Dock;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.home.HomeScreenHandler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import static views.screen.home.HomeScreenHandler.LOGGER;

public class DockDetailScreenHandler extends BaseScreenHandler{

    public class Wrapper{
        private Bike bike;
        private int index;

        public Wrapper(Bike bike, int index){
            this.bike = bike;
            this.index = index+1;
        }

        public StringProperty statusProperty(){
            if (this.bike.isStatus()) {
                return new SimpleStringProperty("Đang thuê");
            }
            return new SimpleStringProperty("Có sẵn");
        }
        public Integer getIndex(){
            return this.index;
        }
        public String getBarcode(){
            return String.valueOf(this.bike.getBikeId());
        }
        public String getCategory(){
            return this.bike.getCategory().getCategoryName();
        }
        public int getBikeId(){
            return this.bike.getBikeId();
        }
        public String getBikeName(){
            return this.bike.getBikeName();
        }
        public Bike getBike(){
            return this.bike;
        }
    }
    @FXML
    private Button backBtn;
    @FXML
    private TableView<Wrapper> bikeListTable;
    @FXML
    private TableColumn<Wrapper, Integer> index, bikeId;
    @FXML
    private TableColumn<Wrapper, String> barcode, bikeName, category, status;
    @FXML
    private SplitMenuButton searchBikeBtn;
    @FXML
    private TextField searchBikeTextField;
    @FXML
    private ImageView logo, dockImage, backIcon;
    @FXML
    private Label titleLabel;


    private int dockID;
    private static int searchOption=-1;

    public int getDockID() {
        return dockID;
    }

    public void setDockID(int dockID) {
        this.dockID = dockID;
    }

    public DockDetailScreenHandler(Stage stage, String screenPath, int dockID) throws IOException {
        super(stage, screenPath);
        this.dockID = dockID;

        // Set image:
        super.setImage(true);
        // BackIcon:
        super.setSingleImage(backIcon,Configs.IMAGE_PATH + "/" +"capstone_backarrow.jpg");
        // Set title
        super.setScreenTitle("Dock Detail");
    }

    public void initiate(){
        System.out.println((DockDetailScreenHandler) this.loader.getController());

        DockController dockController = (DockController) getBController();
        Dock dock = dockController.getDock(this.dockID);
        // Set title, image and label name:
        titleLabel.setText("Bãi xe " + dock.getDockName());
        this.setSingleImage(dockImage,Configs.IMAGE_PATH + "/dock/" +dock.getImagePath());

        // Load data to table:
        List<Bike> bikes = dockController.getBikeByDockId(this.dockID);
        this.loadDataToBikeTable(bikes);

        // Search option:
        ObservableList<MenuItem> menuItems = searchBikeBtn.getItems();
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            int finalI = i;
            if (i == menuItems.size() - 1) {
                menuItem.setOnAction((e) -> {
                    this.loadDataToBikeTable(bikes);
                });
            } else {
                menuItem.setOnAction((e) -> {
                    searchOption = finalI;
                });
            }
        }
    }

    public void loadDataToBikeTable(List<Bike> bikes){
        ObservableList<Wrapper> wrapper = FXCollections.observableArrayList();
        for (int i = 0; i < bikes.size(); i++){
            wrapper.add(new Wrapper(bikes.get(i), i));
        }
        // Thêm thông tin dock vào bảng:

        index.setCellValueFactory(new PropertyValueFactory<Wrapper, Integer>("index"));
        bikeId.setCellValueFactory(new PropertyValueFactory<Wrapper, Integer>("bikeId"));
        barcode.setCellValueFactory(new PropertyValueFactory<Wrapper, String>("barcode"));
        status.setCellValueFactory(new PropertyValueFactory<Wrapper, String>("status"));
        bikeName.setCellValueFactory(new PropertyValueFactory<Wrapper, String>("bikeName"));
        category.setCellValueFactory(new PropertyValueFactory<Wrapper, String>("category"));
        bikeListTable.setItems(wrapper);
    }

    @FXML
    public void goBackPreviousScreen(ActionEvent event) {
        /**
         * Handle event when user click back button, go back to home screen
         */
        System.out.println("User clicked back icon!");
        this.getPreviousScreen().show();
    }

    @FXML
    public void goBikeDetailScreen(MouseEvent event) throws IOException{
        /**
         * Handle event when user click on a bike in list bike of dock, go to the detail bike information screen
         */
        Bike bike = bikeListTable.getSelectionModel().getSelectedItem().getBike();
        BikeDetailScreenHandler bikeDetailScreen;
        try {
            LOGGER.info("User clicked to view a dock");
            bikeDetailScreen = new BikeDetailScreenHandler(this.stage, Configs.BIKE_DETAIL_SCREEN_PATH, bike);
            bikeDetailScreen.setHomeScreenHandler(this.homeScreenHandler);
            bikeDetailScreen.setBController(new BikeController());
            bikeDetailScreen.setPreviousScreen(this);
            bikeDetailScreen.initiate();
            bikeDetailScreen.show();
        } catch (IOException e1){
            LOGGER.info("Errors occured: " + e1.getMessage());
            e1.printStackTrace();
        }
    }

    @FXML
    void searchBike(ActionEvent event) {
        String info = searchBikeTextField.getText();
        int option = searchOption;
        if (option != -1 && option != 4){
            String opt = searchBikeBtn.getItems().get(option).getText();
            opt = opt.substring(0, 1).toLowerCase() + opt.substring(1);
            DockController dockController = (DockController) getBController();
            List<Bike> bikes = dockController.searchBike(opt, info, this.dockID);
            this.loadDataToBikeTable(bikes);
        }
    }
}

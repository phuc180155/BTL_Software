package views.screen.home;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

import accessor.DockAccessor;
import common.exception.ViewCartException;
import controller.BaseController;
import controller.HomeController;

import entity.Dock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;

import javax.print.Doc;

public class HomeScreenHandler extends BaseScreenHandler implements Initializable{

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    @FXML
    private TableColumn<Dock, String> address;

    @FXML
    private TableColumn<Dock, Integer> availableBikes;

    @FXML
    private TextField barcodeTextField;

    @FXML
    private TableColumn<Dock, Float> distance;

    @FXML
    private TableColumn<Dock, Float> dockArea;

    @FXML
    private TableView<Dock> dockInfoTable;

    @FXML
    private TableColumn<Dock, String> dockName;

    @FXML
    private TableColumn<Dock, Integer> emptyDockingPoints;

    @FXML
    private TableColumn<Dock, Integer> dockId;

    @FXML
    private TableColumn<Dock, Float> walkingTime;

    @FXML
    private ImageView logo;

    @FXML
    private Button rentBikeBtn;

    @FXML
    private Button rentedBikeCartBtn;

    @FXML
    private SplitMenuButton searchDockBtn;

    @FXML
    private TextField searchDockTextField;

    @FXML
    private ImageView footImage11, footImage12, footImage13;

    @FXML
    private ImageView footImage21, footImage22;

    @FXML
    private ImageView rentedBikeIcon;

    @FXML
    private Pagination paginationHomescreen;
    private static final int rowsPerPage = 10;

    private static int searchOption = -1;
    private static final DockAccessor dockAccessor = new DockAccessor();

    public HomeScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
        setBController(new HomeController());
    }

//    public Label getNumMediaCartLabel(){
//        return this.numMediaInCart;
//    }

    @Override
    public void show(){
//        numMediaInCart.setText(String.valueOf(Cart.getCart().getListMedia().size()) + " media");
        super.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            setBController(new HomeController());
            HomeController homeController = (HomeController) this.bController;
            List<Dock> docks = homeController.getAllDock();
            // Thêm vào list của FXCollections
            this.initDataForDockTable(docks);

            // Search Dock
            ObservableList<MenuItem> menuItems = searchDockBtn.getItems();
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem menuItem = menuItems.get(i);
                int finalI = i;
                if (i == menuItems.size() - 1) {
                    menuItem.setOnAction((e) -> {
                        this.initDataForDockTable(docks);
                    });
                } else {
                    menuItem.setOnAction((e) -> {
                        searchOption = finalI;
                    });
                }
            }
            searchDockBtn.setOnAction((e)->{
                System.out.println(searchOption);
                if (searchOption != -1) {
                    String info = searchDockTextField.getText();
                    List<Dock> dockLst = homeController.searchDockByInformation(searchOption, info);
                    this.initDataForDockTable(dockLst);
                }
            });

        } catch (SQLException e) {
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void goDockDetailScreen(MouseEvent e) throws IOException {
        /**
         * Handle event when user click on station, go to the station detail screen of choosen station
         */
        Dock dock = dockInfoTable.getSelectionModel().getSelectedItem();
        int id = dock.getDockId();
        System.out.println(id);
    }

    public void initDataForDockTable(List<Dock> docks) {
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


//        cartImage.setOnMouseClicked(e -> {
//            CartScreenHandler cartScreen;
//            try {
//                LOGGER.info("User clicked to view cart");
//                cartScreen = new CartScreenHandler(this.stage, Configs.CART_SCREEN_PATH);
//                cartScreen.setHomeScreenHandler(this);
//                cartScreen.setBController(new ViewCartController());
//                cartScreen.requestToViewCart(this);
//            } catch (IOException | SQLException e1) {
//                throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
//            }
//        });

    public void setImage(){
        // Config:
        Map<ImageView, String> map = new HashMap<ImageView, String>();
        map.put(footImage11, "foot1.jpg");
        map.put(footImage12, "foot1.jpg");
        map.put(footImage13, "foot1.jpg");
        map.put(footImage21, "foot2.jpg");
        map.put(footImage22, "foot2.jpg");
        map.put(logo, "logo.jpg");
        map.put(rentedBikeIcon, "rentedBikeCart.png");

        // fix image path caused by fxml
        for (Map.Entry<ImageView, String> entry : map.entrySet()) {
            File f = new File(Configs.IMAGE_PATH + "/" + entry.getValue());
            Image img = new Image(f.toURI().toString());
            entry.getKey().setImage(img);
        }
    }
}
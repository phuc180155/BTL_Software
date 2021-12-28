package views.screen;

import controller.ReturnBikeController;
import controller.UserController;
import entity.Rent;
import javafx.application.Platform;
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
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private ListView<String> timerListView;

    @FXML
    private ImageView rentedBikeListImage;

    private List<Long> totalSecond = new ArrayList<>();
    private Timer timer =  new Timer();
    private int numberItems;

    @FXML
    void goBackPreviousScreen(ActionEvent event) {
        HomeScreenHandler homeScreenHandler = (HomeScreenHandler) this.getPreviousScreen();
        homeScreenHandler.initialize();
        homeScreenHandler.show();
    }

    public RentedBikeListScreenHandler(Stage stage, String screenPath, int userId) throws IOException {
        super(stage, screenPath);
        this.userId = userId;
    }

    public void initiate(){
        // Set image:
        this.setImage(true);
        // BackIcon:
        this.setSingleImage(backIcon,Configs.IMAGE_PATH + "/" +"backarrow.jpg");
        this.setSingleImage(rentedBikeListImage, Configs.IMAGE_PATH + "/rentedBikeListImage.jpg");
        // Set title
        this.setScreenTitle("Rented Bike List Screen");
        // Set title label:
        titleLabel.setText("List of Rented Bikes");
        System.out.println((RentedBikeListScreenHandler) this.loader.getController());
        this.loadData();
    }

    public void loadData(){
        //Rent Listbikerented took from user
        UserController userController = new UserController();
        List<Rent> rentLst = userController.getRentByUserId(this.userId);
        this.numberItems = rentLst.size();

        ObservableList<Rent> items = FXCollections.observableArrayList(rentLst);
        // items: toString method of Rent Class
        rentedBikeListView.setItems(items);
        ObservableList<String> timer = FXCollections.observableArrayList(Collections.nCopies(numberItems, "00:00:00"));
        timerListView.setItems(timer);
        setTimer(rentLst);

    }

    @FXML
    void goReturnBikeScreen(MouseEvent event) {
        Rent rent = rentedBikeListView.getSelectionModel().getSelectedItem();
        if (rent == null)
            return;
        ReturnBikeScreenHandler returnBikeScreenHandler;
        try {
            LOGGER.info("User clicked to return bike");
            returnBikeScreenHandler = new ReturnBikeScreenHandler(this.stage, Configs.CREDIT_CARD_FORM_PATH, rent);
            returnBikeScreenHandler.setHomeScreenHandler(this.homeScreenHandler);
            returnBikeScreenHandler.setScreenTitle("Return Bike Screen");
            returnBikeScreenHandler.setBController(new ReturnBikeController());
            returnBikeScreenHandler.setPreviousScreen(this);
            returnBikeScreenHandler.initiate();
            returnBikeScreenHandler.show();
        } catch (IOException e1){
            LOGGER.info("Errors occured: " + e1.getMessage());
            e1.printStackTrace();
        }
    }


    /**
     * Support for display timer
     * @param value
     * @return
     */
    private static String format(long value){
        if (value < 10){
            return 0 + "" + value;
        }
        return String.valueOf(value);
    }

    private String convertTime(int index){
        long totalSec = totalSecond.get(index);
        long min = TimeUnit.SECONDS.toMinutes(totalSec);
        long sec = totalSec - (min*60);
        long hr = TimeUnit.SECONDS.toHours(totalSec);
        min = min - (hr*60);
        totalSecond.set(index, totalSec + 1);
        return "" + format(hr) + ":" + format(min) + ":" + format(sec);
    }

    public void setTimer(List<Rent> rentLst){
        if (rentLst.isEmpty())
            return;
        Timestamp now = new Timestamp(new Date().getTime());
        for (Rent rent : rentLst) {
            long sec = (long) TimeUnit.MILLISECONDS.toSeconds(now.getTime() - rent.getStartTime().getTime());
            totalSecond.add(sec);
        }
        int numItems = this.numberItems;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Thread t = new Thread(() -> {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < numItems; i++){
                                timerListView.getItems().set(i, convertTime(i));
                            }
                        }
                    });
                });
                t.start();
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

}

package views.screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;


public class ResultScreenHandler extends BaseScreenHandler{

    @FXML
    private Button homeBtn;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView homeIcon;

    @FXML
    void goHomeScreen(ActionEvent event) throws IOException {
        HomeScreenHandler homeHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
        homeHandler.setScreenTitle("Home screen");
        homeHandler.initialize();
        homeHandler.show();
    }

    public ResultScreenHandler(Stage stage, String screenPath) throws IOException{
        super(stage, screenPath);
    }

    public void initiate(String message){
        this.setImage(false);
        this.setSingleImage(homeIcon, Configs.IMAGE_PATH + "/homeicon.png");
        messageLabel.setText(message);
        if (message.contains("success")){
            messageLabel.setStyle("-fx-text-fill: green");
        } else {
            messageLabel.setStyle("-fx-text-fill: red");
        }
    }
}

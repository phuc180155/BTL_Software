package views.screen;

import java.io.File;
import java.io.IOException;
import java.util.*;

import controller.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.home.HomeScreenHandler;

public class BaseScreenHandler {

	@FXML
	private ImageView footImage11, footImage12, footImage13, footImage21, footImage22, logo;

	private Scene scene;
	private BaseScreenHandler prev;
	protected BaseController bController;
	protected final Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	protected AnchorPane content;
	protected FXMLLoader loader;

	private BaseScreenHandler(String screenPath) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(screenPath));
		this.loader.setController(this);
		this.content = (AnchorPane) loader.load();
		this.stage = new Stage();
	}

	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	// Load fxml
	public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		this.loader = new FXMLLoader(getClass().getResource(screenPath));
		this.loader.setController(this);
		this.content = loader.load();
		this.stage = stage;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	public void setBController(BaseController bController){
		this.bController = bController;
	}

	public BaseController getBController(){
		return this.bController;
	}

	public void forward(Hashtable messages) {
		this.messages = messages;
	}

	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}

	public void setImage(boolean click){
		// Config:
		List<ImageView> imgViews = List.of(footImage11, footImage12, footImage13, footImage21, footImage22, logo);
		Map<ImageView, String> map = new HashMap<ImageView, String>();
		int i = 0;
		for (i = 0; i<imgViews.size()-1; i++)
			map.put(imgViews.get(i), "foot"+i+".jpg");
		map.put(imgViews.get(i), "logo.jpg");
		
		if (click){
			logo.setOnMouseClicked(e -> {
				this.homeScreenHandler.show();
			});
		}

		// fix image path caused by fxml
		for (Map.Entry<ImageView, String> entry : map.entrySet()) {
			this.setSingleImage(entry.getKey(), Configs.IMAGE_PATH + "/" + entry.getValue());
		}
	}

	public void setSingleImage(ImageView imgView, String imgPath){
		File f = new File(imgPath);
		Image img = new Image(f.toURI().toString());
		imgView.setImage(img);
		imgView.setBlendMode(BlendMode.MULTIPLY);
	}

	public String convertCurrencyFormat(float p){
		String price = ""+ Math.round(p);
		if (price.length() <= 3)
			return price;
		StringBuilder newFormat = new StringBuilder();
		for (int i = 0; i<price.length()%3; i++)
			newFormat.append(price.charAt(i));
		for (int i = price.length()%3; i<price.length(); i+=3)
			newFormat.append(new char[]{'.', price.charAt(i), price.charAt(i + 1), price.charAt(i + 2)});
		return (newFormat.toString().charAt(0) == '.') ? newFormat.toString().substring(1) : newFormat.toString();
	}
}

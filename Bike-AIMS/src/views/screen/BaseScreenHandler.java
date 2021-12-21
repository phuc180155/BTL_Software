package views.screen;

import java.io.IOException;
import java.util.Hashtable;

import controller.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;

public class BaseScreenHandler {

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

}

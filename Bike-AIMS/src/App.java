import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;

import utils.Configs;
import views.screen.home.*;

import static java.lang.Math.random;


public class App extends Application {

	@FXML
	ImageView logo;

	@Override
	public void start(Stage primaryStage) {
		try {

			// initialize the scene
			// Khi dùng FXML load file XXX.fxml, XXXController tự động gọi
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			// Add Graphics circle
			Group circles = new Group();
			for (int i = 0; i < 30; i++) {
				Circle circle = new Circle(150, Color.web("white", 0.05));
				circle.setStrokeType(StrokeType.OUTSIDE);
				circle.setStroke(Color.web("white", 0.16));
				circle.setStrokeWidth(4);
				circles.getChildren().add(circle);
			}
			// Add Box Blur Effect
			circles.setEffect(new BoxBlur(10, 10, 3));
			root.getChildren().add(circles);

			// Animation for circles:
			Timeline timeline = new Timeline();
			for (Node circle: circles.getChildren()) {
				timeline.getKeyFrames().addAll(
						new KeyFrame(Duration.ZERO, // set start position at 0
								new KeyValue(circle.translateXProperty(), random() * 800),
								new KeyValue(circle.translateYProperty(), random() * 600)
						),
						new KeyFrame(new Duration(2), // set end position at 40s
								new KeyValue(circle.translateXProperty(), random() * 800),
								new KeyValue(circle.translateYProperty(), random() * 600)
						)
				);
			}
			// play 40s of animation
			timeline.play();

			primaryStage.show();

			// Load splash screen with fade in effect
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);

			// Finish splash with fade out effect
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);

			// After fade in, start fade out
			fadeIn.play();
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});

			// After fade out, load actual content
			fadeOut.setOnFinished((e) -> {
				try {
					HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, Configs.HOME_PATH);
					homeHandler.initialize();
					homeHandler.setScreenTitle("Home Screen");
					homeHandler.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}

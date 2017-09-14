//Main class to set up and test the GUI until we incorporate it with the actual implementation

package seng202.team5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(Main.class.getResource("/seng202/View/LoginScreen.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
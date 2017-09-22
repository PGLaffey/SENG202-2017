//Main class to set up and test the GUI until we incorporate it with the actual implementation

package seng202.team5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.Model.DataFetcher;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(Main.class.getResource("/LoginScreen.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}

	@Override
	public void stop() {
		DataFetcher exporter = new DataFetcher();
		try {
			exporter.connectDb();
			exporter.storeCurrentStorage();
			exporter.closeConnection();
			System.exit(0);
		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
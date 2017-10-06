//Main class to set up and test the GUI until we incorporate it with the actual implementation

package seng202.team5;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.DataFetcher;
import seng202.Model.FileManager;

public class Main extends Application {

	private static Stage mainStage;

	public static Stage getStage() {
		return mainStage;
	}

	/**
	 * Begins the application and loads the GUI
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{
		mainStage = primaryStage;
		Parent root = FXMLLoader.load(Main.class.getResource("/LoginScreen.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}

	
	/**
	 * Overrides the method for when the GUI is closed. Loads newly entered data into database, serializes the user and flushes CurrentStorage
	 */
	@Override
	public void stop() {
		DataFetcher exporter = new DataFetcher();
		try {
			exporter.connectDb();
			exporter.storeCurrentStorage();
			exporter.closeConnection();
			//FileManager.userSerialize(CurrentStorage.getUser(), "./src/main/resources/data_files/");
			CurrentStorage.flush();
			System.exit(0);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
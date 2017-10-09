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

/**
 * Main class to run to start the application.
 */
public class Main extends Application {

	private static Stage mainStage;

	
	/**
	 * Getter for the main window/stage.
	 * @return The main window/stage
	 */
	public static Stage getStage() {
		return mainStage;
	}

	
	/**
	 * Begins the application and loads the GUI
	 * @param primaryStage The initial stage for the app.
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
	 * Overrides the method for when the GUI is closed. Loads newly entered data into database, 
	 * serializes the user and flushes CurrentStorage.
	 */
	@Override
	public void stop() {

	}

	
	/**
	 * Launches the app.
	 * @param args The input parameters when launching the application from command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
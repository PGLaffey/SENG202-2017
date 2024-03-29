package seng202.Controller;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.Model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.File;


import javafx.scene.control.*;

/**
 * Controller class for the tables screen.
 */
public class TablesScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountButton;

    @FXML
    private TableColumn<Location, Double> allLocLatCol;

    @FXML
    private TableColumn<Location, Double> allLocLongCol;

    @FXML
    private TableColumn<Location, String> allLocNameCol;

    @FXML
    private TableColumn<Location, String> allLocTypeCol;

    @FXML
	private TableColumn<Location, String> allLocAddressCol;

    @FXML
	private TableColumn<Location, String> allLocBoroughCol;

    @FXML
    private TableView<Location> allLocationsTable;

    @FXML
    private Button logoutButton;

    @FXML
    private Button mapButton;

    @FXML
    private TableColumn<Poi, Double> poiCostCol;

    @FXML
    private TableColumn<Poi, String> poiDescCol;

    @FXML
    private TableColumn<Poi, String> poiAddressCol;

    @FXML
    private TableColumn<Poi, String> poiBoroughCol;

    @FXML
    private TableColumn<Poi, String> poiNameCol;

    @FXML
    private TableView<Poi> poiTable;

    @FXML
    private TableColumn<Retailer, String> retDescCol;

    @FXML
    private TableColumn<Retailer, String> retAddressCol;

    @FXML
    private TableColumn<Retailer, String> retBoroughCol;

    @FXML
    private TableColumn<Retailer, String> retNameCol;

    @FXML
    private TableColumn<Retailer, String> retProductCol;

    @FXML
    private TableView<Retailer> retailersTable;

    @FXML
    private TableColumn<Route, String> routeDistCol;

    @FXML
    private TableColumn<Route, String> routeEndCol;

    @FXML
    private TableColumn<Route, String> routeNameCol;

    @FXML
    private TableColumn<Route, String> routeStartCol;

    @FXML
    private TableView<Route> routesTable;

    @FXML
    private Button searchButton;
    
    @FXML
    private Button exportButton;

    @FXML
    private Button statButton;

    @FXML
    private Button tableButton;

    @FXML
    private ChoiceBox<String> tableOptions;

    @FXML
    private TextField keywordText;

    @FXML
    private TableColumn<Toilet, Boolean> toiletDisCol;

    @FXML
    private TableColumn<Toilet, String> toiletAddressCol;

    @FXML
    private TableColumn<Toilet, String> toiletBoroughCol;

    @FXML
    private TableColumn<Toilet, String> toiletNameCol;

    @FXML
    private TableColumn<Toilet, Boolean> toiletUniCol;

    @FXML
    private TableView<Toilet> toiletsTable;

    @FXML
    private TableColumn<Wifi, String> wifiAddressCol;

    @FXML
	private TableColumn<Wifi, String> wifiBoroughCol;

    @FXML
    private TableColumn<Wifi, String> wifiNameCol;

    @FXML
    private TableColumn<Wifi, String> wifiProvCol;

    @FXML
    private TableView<Wifi> wifiTable;

    @FXML
    private TableColumn<Wifi, String> wifiTypeCol;

    @FXML
	private Label noDataLabel;
    
    @FXML
    private Label nothingLabel;


    /**
     * Method for when account menu button pressed, shows the profile screen.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void accountPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) accountButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));

		Scene currentScene = primaryStage.getScene();
		Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), 
				primaryStage.getMinHeight()): new Scene(root, currentScene.getWidth(), 
				currentScene.getHeight()));

		primaryStage.setTitle("Profile");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    
    /**
     * Method for when logout button pressed, shows the login screen and flushes current storage.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void logoutPressed(ActionEvent event) throws IOException {
		DataFetcher exporter = new DataFetcher();
		try {
			exporter.connectDb();
			exporter.storeCurrentStorage();
			exporter.closeConnection();
			CurrentStorage.flush();
			System.exit(0);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
    }

    
    /**
     * Method for when the map menu button pressed, shows main map screen.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void mapPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) mapButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));

		Scene currentScene = primaryStage.getScene();
		Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), 
				primaryStage.getMinHeight()): new Scene(root, currentScene.getWidth(), 
				currentScene.getHeight()));

		primaryStage.setTitle("Map");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    

    /**
     * Method when the search button is pressed. Displays the correct table and filtering.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void searchPressed(ActionEvent event) {
    	String option = tableOptions.getValue().toString();
    	if (option == "All locations") {
    		allLocationsTable.refresh();
    		ArrayList<Location> locations = new ArrayList<Location>();
    		locations.addAll(CurrentStorage.getPoiArray());
    		locations.addAll(CurrentStorage.getRetailerArray());
    		locations.addAll(CurrentStorage.getToiletArray());
    		locations.addAll(CurrentStorage.getWifiArray());
    		locations.addAll(CurrentStorage.getGeneralArray());

    		ArrayList<Location> locFiltered = RawDataViewer.searchLocations(locations, 
    				keywordText.getText().toString());
    		ObservableList<Location> locData = FXCollections.observableArrayList(locFiltered);
    		allLocationsTable.setItems(locData);
    		if (allLocationsTable.getItems().isEmpty()) {
				allLocationsTable.setVisible(false);
				noDataLabel.setVisible(true);
			} 
    		else {
				allLocationsTable.setVisible(true);
				noDataLabel.setVisible(false);
			}
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} 
    	else if (option == "Retailers") {
    		retailersTable.refresh();
    		ArrayList<Retailer> retFiltered = RawDataViewer.searchRetailer(
    				CurrentStorage.getRetailerArray(), keywordText.getText().toString());
    		ObservableList<Retailer> retData = FXCollections.observableArrayList(retFiltered);
        	retailersTable.setItems(retData);
        	if (retailersTable.getItems().isEmpty()) {
				retailersTable.setVisible(false);
				noDataLabel.setVisible(true);
			} 
        	else {
				retailersTable.setVisible(true);
				noDataLabel.setVisible(false);
			}
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} 
    	else if (option == "WiFi") {
    		wifiTable.refresh();
    		ArrayList<Wifi> wifiFiltered = RawDataViewer.searchWifi(
    				CurrentStorage.getWifiArray(), keywordText.getText().toString());
    		ObservableList<Wifi> wifiData = FXCollections.observableArrayList(wifiFiltered);
    		wifiTable.setItems(wifiData);
    		if (wifiTable.getItems().isEmpty()) {
    			noDataLabel.setVisible(true);
				wifiTable.setVisible(false);

			} 
    		else {
				wifiTable.setVisible(true);
				noDataLabel.setVisible(false);
			}
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} 
    	else if (option == "Toilets") {
    		toiletsTable.refresh();
    		ArrayList<Toilet> toiletFiltered = RawDataViewer.searchToilets(
    				CurrentStorage.getToiletArray(), keywordText.getText().toString());
    		ObservableList<Toilet> toiletData = FXCollections.observableArrayList(toiletFiltered);
    		toiletsTable.setItems(toiletData);

    		if (toiletsTable.getItems().isEmpty()) {
    			noDataLabel.setVisible(true);
				toiletsTable.setVisible(false);
			}
    		
    		else {
				toiletsTable.setVisible(true);
				noDataLabel.setVisible(false);
			}
			allLocationsTable.setVisible(false);
			poiTable.setVisible(false);
			retailersTable.setVisible(false);
			wifiTable.setVisible(false);
			routesTable.setVisible(false);

    	} 
    	else if (option == "Points of interest")  {
    		poiTable.refresh();
    		ArrayList<Poi> poiFiltered = RawDataViewer.searchPoi(
    				CurrentStorage.getPoiArray(), keywordText.getText().toString());
    		ObservableList<Poi> poiData = FXCollections.observableArrayList(poiFiltered);
    		poiTable.setItems(poiData);
    		if (poiTable.getItems().isEmpty()) {
    			noDataLabel.setVisible(true);
				poiTable.setVisible(false);
			} 
    		else {
    			poiTable.setVisible(true);
    			noDataLabel.setVisible(false);
			}
    		allLocationsTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} 
    	else if (option == "Routes") {
    		routesTable.refresh();
    		ArrayList<Route> routeFiltered = RawDataViewer.searchRoutes(
    				CurrentStorage.getRouteArray(), keywordText.getText().toString());
    		ObservableList<Route> routeData = FXCollections.observableArrayList(routeFiltered);
    		routesTable.setItems(routeData);
    		if (routesTable.getItems().isEmpty()) {
    			noDataLabel.setVisible(true);
    			routesTable.setVisible(false);
			} 
    		else {
    			noDataLabel.setVisible(false);
    			routesTable.setVisible(true);
			}
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    	}
    }


    /**
     * Method when the statistics menu button pressed, shows the statistics screen.
     * @param event - Auto-generate event on button press
	
     */
    @FXML
    void statPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) statButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));

		Scene currentScene = primaryStage.getScene();
		Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), 
				primaryStage.getMinHeight()): new Scene(root, currentScene.getWidth(), 
				currentScene.getHeight()));

		primaryStage.setTitle("Statistics");
		primaryStage.setScene(scene);
		primaryStage.show();
    }


	/**
	 * Method for when the table menu button is pressed, shows the table screen.
	 * Displays pop up if an error occurs.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
    void tablePressed(ActionEvent event) throws IOException {
		try {
			Stage primaryStage = (Stage) tableButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/TablesScreen.fxml"));

			Scene currentScene = primaryStage.getScene();
			Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), 
					primaryStage.getMinHeight()): new Scene(root, currentScene.getWidth(), 
					currentScene.getHeight()));

			primaryStage.setTitle("Table");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch (Exception e){
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/DataLoadingScreen.fxml"));

			Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
			stage.setTitle("Still Loading");
			stage.setScene(scene);
			stage.show();
		}
    }

    
	/**
	 * Method when a row in the wifi table is pressed, displays a pop up with further information.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
    void wifiTableClicked(MouseEvent event) throws IOException {
    	Wifi row = wifiTable.getSelectionModel().getSelectedItem();
    	CurrentStorage.setWifiIndex(CurrentStorage.getWifiArray().indexOf(row));
    	Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/WifiInfoScreen.fxml"));


		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("About wifi point");
		stage.setScene(scene);
		stage.show();
    }

	
	/**
	 * Method when a row in the retailer table is pressed, displays a pop up with further 
	 * information.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
    void retailerTableClicked(MouseEvent event) throws IOException {
    	Retailer row = retailersTable.getSelectionModel().getSelectedItem();
    	CurrentStorage.setRetailerIndex(CurrentStorage.getRetailerArray().indexOf(row));
    	Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/RetailerInfoScreen.fxml"));

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("About retailer point");
		stage.setScene(scene);
		stage.show();
    }

	
	/**
	 * Method when a row in the toilet table is pressed, displays a pop up with further 
	 * information.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
    void toiletTableClicked(MouseEvent event) throws IOException {
    	Toilet row = toiletsTable.getSelectionModel().getSelectedItem();
    	CurrentStorage.setToiletIndex(CurrentStorage.getToiletArray().indexOf(row));
    	Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ToiletInfoScreen.fxml"));

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("About toilet point");
		stage.setScene(scene);
		stage.show();
    }

	
	/**
	 * Method when a row in the all locations table is pressed, displays a pop up with further 
	 * information.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
    void allLocationTableClicked(MouseEvent event) throws IOException {
    	Location row = allLocationsTable.getSelectionModel().getSelectedItem();
    	CurrentStorage.setLocation(row);
    	Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/LocationInfoScreen.fxml"));

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("About location point");
		stage.setScene(scene);
		stage.show();
    }
	

	/**
	 * Method when a row in the route table is pressed, displays a pop up with further 
	 * information.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
    void routeTableClicked(MouseEvent event) throws IOException {
    	Route row = routesTable.getSelectionModel().getSelectedItem();
    	CurrentStorage.setRoute(row);
    	Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/RouteInfoScreen.fxml"));

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("About route");
		stage.setScene(scene);
		stage.show();
    }

	
	/**
	 * Method when a row in the point of interest table is pressed, displays a pop up with 
	 * further information.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
    void poiTableClicked(MouseEvent event) throws IOException {
    	Poi row = poiTable.getSelectionModel().getSelectedItem();
    	CurrentStorage.setPoiIndex(CurrentStorage.getPoiArray().indexOf(row));
    	Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/PoiInfoScreen.fxml"));

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("About point of interest");
		stage.setScene(scene);
		stage.show();
    }


	/**
	 * Method for when the export button is pressed. Exports the data currently in the table.
	 * @param event - Auto-generate event on button press
	 */
	@FXML
	void exportPressed(ActionEvent event)  {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Export Data");
		if (noDataLabel.isVisible()) {
			nothingLabel.setVisible(true);
		} 
		else if (allLocationsTable.isVisible()) {
			nothingLabel.setVisible(false);
			ArrayList<Location> locData =  new ArrayList<Location>(allLocationsTable.getItems());
			File file = fileChooser.showSaveDialog(null);
		} 
		else if (poiTable.isVisible()) {
			nothingLabel.setVisible(false);
			ArrayList<Poi> poiData = new ArrayList<Poi>(poiTable.getItems());
			File file = fileChooser.showSaveDialog(null);
			FileManager.poiWriter(file.toString() + ".csv", poiData);
		}
		else if (retailersTable.isVisible()) {
			nothingLabel.setVisible(false);
			ArrayList<Retailer> retData = new ArrayList<Retailer>(retailersTable.getItems());
			File file = fileChooser.showSaveDialog(null);
			FileManager.retailerWriter(file.toString() + ".csv", retData);
		}
		else if (wifiTable.isVisible()) {
			nothingLabel.setVisible(false);
			ArrayList<Wifi> wifiData = new ArrayList<Wifi>(wifiTable.getItems());
			File file = fileChooser.showSaveDialog(null);
			FileManager.wifiWriter(file.toString() + ".csv", wifiData);
		} 
		else if (toiletsTable.isVisible()) {
			nothingLabel.setVisible(false);
			ArrayList<Toilet> toiletData = new ArrayList<Toilet>(toiletsTable.getItems());
			File file = fileChooser.showSaveDialog(null);
			FileManager.toiletWriter(file.toString() + ".csv", toiletData);
		} 
		else if (routesTable.isVisible()) {
			nothingLabel.setVisible(false);
			ArrayList<Route> routeData = new ArrayList<Route>(routesTable.getItems());
			File file = fileChooser.showSaveDialog(null);
			FileManager.routeWriter(file.toString() + ".csv", routeData);
		}
	}
	

    /**
     * Initialises all the nodes. Also fills all columns in each tables with the correct 
     * objects from current storage.
     */
    @FXML
    void initialize() {
    	
    	noDataLabel.setVisible(true);
    	nothingLabel.setVisible(false);

    	// Originally
    	allLocationsTable.setVisible(false);
		poiTable.setVisible(false);
		retailersTable.setVisible(false);
		wifiTable.setVisible(false);
		toiletsTable.setVisible(false);
		routesTable.setVisible(false);

    	ObservableList<String> filterOptions = FXCollections.observableArrayList("All locations",
    			"Routes", "Retailers", "WiFi", "Toilets", "Points of interest");
    	tableOptions.setItems(filterOptions);
    	tableOptions.getSelectionModel().selectFirst();

    	// Set up the all locations table
    	allLocNameCol.setCellValueFactory(new PropertyValueFactory<Location,String>("name"));
    	allLocTypeCol.setCellValueFactory(
    			new PropertyValueFactory<Location,String>("typeString"));
		allLocAddressCol.setCellValueFactory(
				new PropertyValueFactory<Location, String>("address"));
		allLocBoroughCol.setCellValueFactory(
				new PropertyValueFactory<Location, String>("borough"));

    	// Set up the toilets table
    	toiletDisCol.setCellValueFactory(
    			new PropertyValueFactory<Toilet, Boolean>("forDisabled"));
    	toiletAddressCol.setCellValueFactory(
    			new PropertyValueFactory<Toilet, String>("address"));
    	toiletBoroughCol.setCellValueFactory(
    			new PropertyValueFactory<Toilet, String>("borough"));
    	toiletUniCol.setCellValueFactory(
    			new PropertyValueFactory<Toilet, Boolean>("uniSex"));
    	toiletNameCol.setCellValueFactory(
    			new PropertyValueFactory<Toilet, String>("name"));

    	// Set up the wifi table
    	wifiProvCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("provider"));
    	wifiTypeCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("type"));
    	wifiNameCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("name"));
		wifiAddressCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("address"));
    	wifiBoroughCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("borough"));

    	// Set up the routes table
    	routeEndCol.setCellValueFactory(new PropertyValueFactory<Route, String>("endString"));
    	routeStartCol.setCellValueFactory(new PropertyValueFactory<Route, String>("startString"));
    	routeDistCol.setCellValueFactory(new PropertyValueFactory<Route, String>("distanceRound"));
    	routeNameCol.setCellValueFactory(new PropertyValueFactory<Route, String>("name"));

    	// Set up the retailers table
    	retAddressCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("address"));
    	retBoroughCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("borough"));
    	retDescCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("description"));
    	retProductCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("product"));
    	retNameCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("name"));

    	// Set up the poi table
    	poiAddressCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("address"));
    	poiBoroughCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("borough"));
    	poiDescCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("description"));
    	poiNameCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("name"));
    	poiCostCol.setCellValueFactory(new PropertyValueFactory<Poi, Double>("cost"));
    	ObservableList<Poi> poiData = FXCollections.observableArrayList(
    			CurrentStorage.getPoiArray());
    	poiTable.setItems(poiData);

    	assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your "
    			+ "FXML file 'TablesScreen.fxml'.";
        assert allLocLatCol != null : "fx:id=\"allLocLatCol\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert allLocLongCol != null : "fx:id=\"allLocLongCol\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert allLocNameCol != null : "fx:id=\"allLocNameCol\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert allLocTypeCol != null : "fx:id=\"allLocTypeCol\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert allLocationsTable != null : "fx:id=\"allLocationsTable\" was not injected: check "
        		+ "your FXML file 'TablesScreen.fxml'.";
        assert keywordText != null : "fx:id=\"conditionText\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert mapButton != null : "fx:id=\"mapButton\" was not injected: check your FXML file "
        		+ "'TablesScreen.fxml'.";
        assert poiCostCol != null : "fx:id=\"poiCostCol\" was not injected: check your FXML file"
        		+ " 'TablesScreen.fxml'.";
        assert poiDescCol != null : "fx:id=\"poiDescCol\" was not injected: check your FXML file"
        		+ " 'TablesScreen.fxml'.";
        assert poiNameCol != null : "fx:id=\"poiNameCol\" was not injected: check your FXML file"
        		+ " 'TablesScreen.fxml'.";
        assert poiTable != null : "fx:id=\"poiTable\" was not injected: check your FXML file "
        		+ "'TablesScreen.fxml'.";
        assert retDescCol != null : "fx:id=\"retDescCol\" was not injected: check your FXML file"
        		+ " 'TablesScreen.fxml'.";
        assert retNameCol != null : "fx:id=\"retNameCol\" was not injected: check your FXML file"
        		+ " 'TablesScreen.fxml'.";
        assert retProductCol != null : "fx:id=\"retProductCol\" was not injected: check your "
        		+ "FXML file 'TablesScreen.fxml'.";
        assert retailersTable != null : "fx:id=\"retailersTable\" was not injected: check your "
        		+ "FXML file 'TablesScreen.fxml'.";
        assert routeDistCol != null : "fx:id=\"routeDistCol\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert routeEndCol != null : "fx:id=\"routeEndCol\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert routeNameCol != null : "fx:id=\"routeNameCol\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert routeStartCol != null : "fx:id=\"routeStartCol\" was not injected: check your "
        		+ "FXML file 'TablesScreen.fxml'.";
        assert routesTable != null : "fx:id=\"routesTable\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert statButton != null : "fx:id=\"statButton\" was not injected: check your FXML file"
        		+ " 'TablesScreen.fxml'.";
        assert tableButton != null : "fx:id=\"tableButton\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert tableOptions != null : "fx:id=\"tableOptions\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert toiletDisCol != null : "fx:id=\"toiletDisCol\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert toiletNameCol != null : "fx:id=\"toiletNameCol\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert toiletUniCol != null : "fx:id=\"toiletUniCol\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert toiletsTable != null : "fx:id=\"toiletsTable\" was not injected: check your FXML"
        		+ " file 'TablesScreen.fxml'.";
        assert wifiNameCol != null : "fx:id=\"wifiNameCol\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert wifiProvCol != null : "fx:id=\"wifiProvCol\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
        assert wifiTable != null : "fx:id=\"wifiTable\" was not injected: check your FXML file "
        		+ "'TablesScreen.fxml'.";
        assert wifiTypeCol != null : "fx:id=\"wifiTypeCol\" was not injected: check your FXML "
        		+ "file 'TablesScreen.fxml'.";
    }
}

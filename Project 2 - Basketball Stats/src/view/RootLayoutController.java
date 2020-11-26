package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MainApp;

public class RootLayoutController {
	
    // Reference to the main application
    private MainApp mainApp;
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Basketball Statistics");
    	alert.setHeaderText("About");
    	alert.setContentText("Author: Fall 2020 VSU CS 4321 Team 2\nEvan Steedley\nErskine Denson\nTiffany Olson\nRahmel Gordon\nZan Hardy");

    	alert.showAndWait();
    }
    
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    /*
     * Switches to the PlayersOverview view.
     */
    @FXML
    private void switchToPlayersOverview() {
    	mainApp.showPlayersOverview();
    }
    
    /*
     * Switches to the PlayersOverview view.
     */
    @FXML
    private void switchToTeamsOverview() {
    	mainApp.showTeamsOverview();
    }
    
}

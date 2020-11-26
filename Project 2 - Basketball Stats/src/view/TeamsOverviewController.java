package view;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import model.MainApp;
import model.Player;
import model.Team;

public class TeamsOverviewController {
	
    @FXML
    private TableView<Team> teamTable;
    @FXML
    private TableColumn<Team, String> cityColumn;
    @FXML
    private TableColumn<Team, Number> playerCountColumn;
    @FXML
    private TableColumn<Team, Number> avgAgeColumn;
    @FXML
    private TableColumn<Team, Number> avgPointsPerGameColumn;
    @FXML
    private TableColumn<Team, Number> avgReboundsPerGameColumn;
    @FXML
    private TableColumn<Team, Number> avgAssistsPerGameColumn;
    @FXML
    private TableColumn<Team, Number> avgStealsPerGameColumn;
    @FXML
    private TableColumn<Team, Number> avgBlocksPerGameColumn;
    @FXML
    private TableColumn<Team, Number> avgTurnoversPerGameColumn;
    @FXML
    private Button stopComparing;
    @FXML
    private Button compareToOtherTeams;
    Callback<TableColumn<Team, Number>, TableCell<Team, Number>> defaultCellFactory;

	
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        teamTable.setItems(mainApp.getTeamData());
    }

    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the player table with the 10 columns.
    	cityColumn.setCellValueFactory(
                cellData -> cellData.getValue().getCityProperty());
        playerCountColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNumOfPlayersProperty());
        avgAgeColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAageProperty());
        avgPointsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAppgProperty());
        avgReboundsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getArpgProperty());
        avgAssistsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAapgProperty());
        avgStealsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAspgProperty());
        avgBlocksPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAbpgProperty());
        avgTurnoversPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAtpgProperty());
        
        /*
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        playerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        */
    }
    
    /**
     * Called when the user clicks the Compare to Other Players button.  Updates other players'
     * stats to show positive or negative changes in regards to the selected player.
     */
    @FXML
    private void handleCompareTeamToOthers() {
        Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            ObservableList<Team> teams = mainApp.getTeamData();
            ObservableList<Team> copyOfTeams = FXCollections.observableArrayList();
            for(Team t : teams)
            {
            	Team pt = new Team(t);
            	copyOfTeams.add(pt);
            }
            teamTable.setItems(copyOfTeams);
            defaultCellFactory = avgAgeColumn.getCellFactory();
            
            customiseFactory(avgAgeColumn);
            customiseFactory(avgPointsPerGameColumn);
            customiseFactory(avgReboundsPerGameColumn);
            customiseFactory(avgAssistsPerGameColumn);
            customiseFactory(avgStealsPerGameColumn);
            customiseFactory(avgBlocksPerGameColumn);
            customiseFactory(avgTurnoversPerGameColumn);
            customiseFactory(playerCountColumn);
            for (Team t : copyOfTeams)
            {
            	if(t.equals(selectedTeam))
            		continue;
            	t.setAage(t.getAage() - selectedTeam.getAage());
            	t.setAapg(t.getAapg() - selectedTeam.getAapg());
            	t.setAbpg(t.getAbpg() - selectedTeam.getAbpg());
            	t.setAppg(t.getAppg() - selectedTeam.getAppg());
            	t.setArpg(t.getArpg() - selectedTeam.getArpg());
            	t.setAspg(t.getAspg() - selectedTeam.getAspg());
            	t.setAtpg(t.getAtpg() - selectedTeam.getAtpg());
            	t.setNumOfPlayers(t.getNumOfPlayers() - selectedTeam.getNumOfPlayers());
            }
            compareToOtherTeams.setDisable(true);
            stopComparing.setDisable(false);

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Team Selected");
            alert.setContentText("Please select a team in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the Compare to Other Players button.  Updates other players'
     * stats to show positive or negative changes in regards to the selected player.
     */
    @FXML
    private void handleStopComparing() {
        compareToOtherTeams.setDisable(false);
        stopComparing.setDisable(true);
        avgAgeColumn.setCellFactory(defaultCellFactory);
        avgPointsPerGameColumn.setCellFactory(defaultCellFactory);
        avgReboundsPerGameColumn.setCellFactory(defaultCellFactory);
        avgAssistsPerGameColumn.setCellFactory(defaultCellFactory);
        avgStealsPerGameColumn.setCellFactory(defaultCellFactory);
        avgTurnoversPerGameColumn.setCellFactory(defaultCellFactory);
        avgBlocksPerGameColumn.setCellFactory(defaultCellFactory);
        playerCountColumn.setCellFactory(defaultCellFactory);
        teamTable.setItems(mainApp.getTeamData());
    }
    

/*
 * https://stackoverflow.com/questions/30889732/javafx-tableview-change-row-color-based-on-column-value
 * Used/Edited code from an answer here.
 */
private void customiseFactory(TableColumn<Team, Number> calltypel) {
    calltypel.setCellFactory(column -> {
        return new TableCell<Team, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);

                setText(empty ? "" : getItem().toString());
                setGraphic(null);

                if (!isEmpty()) {

                    if(item.doubleValue() < 0)
                        setStyle("-fx-background-color:lightcoral");
                    else if (item.doubleValue() > 0)
                        setStyle("-fx-background-color:lightgreen");
                }
            }
        };
    });
}

}

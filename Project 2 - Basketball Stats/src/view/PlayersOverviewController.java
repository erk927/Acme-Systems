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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import model.MainApp;
import model.Player;
import model.Team;

public class PlayersOverviewController {
	@FXML
	private TableView<Team> teamTable;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> nameColumn;
    @FXML
    private TableColumn<Player, String> teamColumn;
    @FXML
    private TableColumn<Player, String> positionColumn;
    @FXML
    private TableColumn<Player, Number> ageColumn;
    @FXML
    private TableColumn<Player, Number> pointsPerGameColumn;
    @FXML
    private TableColumn<Player, Number> reboundsPerGameColumn;
    @FXML
    private TableColumn<Player, Number> assistsPerGameColumn;
    @FXML
    private TableColumn<Player, Number> stealsPerGameColumn;
    @FXML
    private TableColumn<Player, Number> blocksPerGameColumn;
    @FXML
    private TableColumn<Player, Number> turnoversPerGameColumn;
    @FXML
    private Button stopComparing;
    @FXML
    private Button compareToOtherPlayers;
    Callback<TableColumn<Player, Number>, TableCell<Player, Number>> defaultCellFactory;

    
    //ObservableList<String> teamList = FXCollections.observableArrayList("Hello", 
    		//"World");
    		//ObservableList<String> teamList = FXCollections.observableArrayList(MainApp.teamCity);
    @FXML
    private ComboBox<String> teamBox;
	
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
        playerTable.setItems(mainApp.getPlayerData());
    }

    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the player table with the 10 columns.
    	nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty());
        teamColumn.setCellValueFactory(
                cellData -> cellData.getValue().teamProperty());
        positionColumn.setCellValueFactory(
                cellData -> cellData.getValue().posProperty());
        ageColumn.setCellValueFactory(
                cellData -> cellData.getValue().ageProperty());
        pointsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().ppgProperty());
        reboundsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().rpgProperty());
        assistsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().apgProperty());
        stealsPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().spgProperty());
        blocksPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().bpgProperty());
        turnoversPerGameColumn.setCellValueFactory(
                cellData -> cellData.getValue().tpgProperty());
        
        /*
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        playerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        */
        
       // teamBox.setValue("Hello");
        //teamBox.setItems(teamList);
        
    }
    
    /**
     * Called when the user clicks the Compare to Other Players button.  Updates other players'
     * stats to show positive or negative changes in regards to the selected player.
     */
    @FXML
    private void handleComparePlayerToOthers() {
        Player selectedPerson = playerTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            ObservableList<Player> players = mainApp.getPlayerData();
            ObservableList<Player> copyOfPlayers = FXCollections.observableArrayList();
            for(Player p : players)
            {
            	Player pt = new Player(p);
            	copyOfPlayers.add(pt);
            }
            playerTable.setItems(copyOfPlayers);
            defaultCellFactory = ageColumn.getCellFactory();
            
            customiseFactory(ageColumn);
            customiseFactory(pointsPerGameColumn);
            customiseFactory(reboundsPerGameColumn);
            customiseFactory(assistsPerGameColumn);
            customiseFactory(stealsPerGameColumn);
            customiseFactory(blocksPerGameColumn);
            customiseFactory(turnoversPerGameColumn);
            for (Player p : copyOfPlayers)
            {
            	if(p.equals(selectedPerson))
            		continue;
            	p.setAge(p.getAge() - selectedPerson.getAge());
            	p.setApg(p.getApg() - selectedPerson.getApg());
            	p.setBpg(p.getBpg() - selectedPerson.getBpg());
            	p.setPpg(p.getPpg() - selectedPerson.getPpg());
            	p.setRpg(p.getRpg() - selectedPerson.getRpg());
            	p.setSpg(p.getSpg() - selectedPerson.getSpg());
            	p.setTpg(p.getTpg() - selectedPerson.getTpg());
            }
            compareToOtherPlayers.setDisable(true);
            stopComparing.setDisable(false);

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Player Selected");
            alert.setContentText("Please select a player in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the Compare to Other Players button.  Updates other players'
     * stats to show positive or negative changes in regards to the selected player.
     */
    @FXML
    private void handleStopComparing() {
        compareToOtherPlayers.setDisable(false);
        stopComparing.setDisable(true);
        ageColumn.setCellFactory(defaultCellFactory);
        pointsPerGameColumn.setCellFactory(defaultCellFactory);
        reboundsPerGameColumn.setCellFactory(defaultCellFactory);
        assistsPerGameColumn.setCellFactory(defaultCellFactory);
        stealsPerGameColumn.setCellFactory(defaultCellFactory);
        turnoversPerGameColumn.setCellFactory(defaultCellFactory);
        blocksPerGameColumn.setCellFactory(defaultCellFactory);
        playerTable.setItems(mainApp.getPlayerData());
    }
    @FXML
    private void handleSelectedTeam() {
        Object selectedTeam = teamBox.getSelectionModel().getSelectedItem();
    	if(selectedTeam!=null) {
    		ObservableList<Player> players = mainApp.getPlayerData();
            ObservableList<Player> copyOfPlayers = FXCollections.observableArrayList();
            for(Player p : players)
            {
            	Player pt = new Player(p);
            	if(p.getTeam().equals(selectedTeam)) {
            		copyOfPlayers.add(pt);
            		continue;
            }
            playerTable.setItems(copyOfPlayers);
            
            }
    	}
           
         else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Player Selected");
            alert.setContentText("Please select a player in the table.");

            alert.showAndWait();
        }
    }
 	
   
    

/*
 * https://stackoverflow.com/questions/30889732/javafx-tableview-change-row-color-based-on-column-value
 * Used/Edited code from an answer here.
 */
private void customiseFactory(TableColumn<Player, Number> calltypel) {
    calltypel.setCellFactory(column -> {
        return new TableCell<Player, Number>() {
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

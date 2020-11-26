package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import view.PlayersOverviewController;
import view.RootLayoutController;
import view.TeamsOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	/** Variables **************************/
	private Stage primaryStage;
    private BorderPane rootLayout;
	private static ObservableList<Player> players = FXCollections.observableArrayList();
	private static ObservableList<Team> teams = FXCollections.observableArrayList();
	private static ObservableList<String> teamCity = FXCollections.observableArrayList();
	
    /*****************************************************************************************************************
     * Where it all begins
    *****************************************************************************************************************/
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Basketball Statistics");

        initRootLayout();	
        
        showPlayersOverview();
	}
	
	/*******************************************************************************************************************
	 * Returns the main stage.
	 * @return
	 ******************************************************************************************************************/
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
    /*****************************************************************************************************************
     * main
    *****************************************************************************************************************/
	public static void main(String[] args) {
            launch(args);
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------------
//												Data Control Methods
//-----------------------------------------------------------------------------------------------------------------------------------------------
    /*******************************************************************************************************************
     * Populates players with player info from data.csv
     ******************************************************************************************************************/
    private static void populateplayers(Scanner input) {
		//Gets player info and creates new player
		while (input.hasNextLine()) {
			String[] s1 = input.nextLine().split(",");
				players.add(new Player(s1[0],s1[1],s1[2],Double.valueOf(s1[3]),Double.valueOf(s1[4]),
						Double.valueOf(s1[5]),Double.valueOf(s1[6]),Double.valueOf(s1[7]),
						Double.valueOf(s1[8]),Double.valueOf(s1[9])));
			}
		
    	populateteams();//After players is filled, create teams and add players to their team
    }
    
    /*******************************************************************************************************************
     * Populates teams with player info from data.csv
     ******************************************************************************************************************/
    private static void populateteams() {
    	teamCity.add("All Players");//Add as first option in list
    	for (int i = 0; i < players.size(); i++) 
		{
			//If Team isn't on teamCity list, create team
			if (!(teamCity.contains(players.get(i).getTeam()))) 
			{
				teamCity.add(players.get(i).getTeam());
				Team team = new Team(players.get(i).getTeam());
				//team.setCity(players.get(i).getTeam());
				teams.add(team);
			}
			//Adds player to the correct Team
			for(int j = 0; j < teams.size(); j++) 
			{
				if (players.get(i).getTeam().matches(teams.get(j).getCity())) {
					teams.get(j).getRoster().add(players.get(i));
				}
			}
		}	
	}
	
    /**************************************************************** 
     * Returns players list
     ***************************************************************/
	public ObservableList<Player> getPlayerData(){
		return players;
	}
	
    /**************************************************************** 
     * Returns teams list
     ***************************************************************/
	public ObservableList<Team> getTeamData(){
		return teams;
	}
	
    /**************************************************************** 
     * Returns teamCity list
     ***************************************************************/
    public ObservableList<String> getCityList(){
    	return teamCity;
    }
    
    /*******************************************************************************************************************
     * Constructor: Reads data file and calls method that populates lists
     ******************************************************************************************************************/
	public MainApp() {
        try {
        //Scanner reads csv file with player stats
        Scanner input = new Scanner(new File("data.csv"));
        populateplayers(input);
        }
        catch(FileNotFoundException fnfe) {
        	System.err.println(fnfe.getMessage());
        }
	}
	
    /**************************************************************** 
     * Print players list
     ***************************************************************/
	public static void displayPlayer() {
		System.out.println("Number of Players: " + players.size());
		System.out.printf("%-25s%-19s%-19s\n", "Name:", "Team:", "Position:");
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).toString());
		}
	}
	
    /**************************************************************** 
     * Print team list
     ***************************************************************/
	public static void displayTeams() {
		for (int i = 0; i < teams.size(); i++) {
			System.out.println("City: " + teams.get(i).getCity());
			System.out.println("Number of Players: " + teams.get(i).getRoster().size());
			System.out.printf("%-25s%-19s%-19s\n", "Name:", "Team:", "Position:");
			for (int j = 0; j < teams.get(i).getRoster().size(); j++) {
				System.out.println(teams.get(i).getRoster().get(j).toString());
			}
			System.out.println();
		}
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------------
//																Views
//-----------------------------------------------------------------------------------------------------------------------------------------------
    /*******************************************************************************************************************
     * Initialize the Root layout
     ******************************************************************************************************************/
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane)loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /*******************************************************************************************************************
     * Shows the Players overview inside the root layout.
     ******************************************************************************************************************/
    public void showPlayersOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/PlayersOverview.fxml"));
            AnchorPane playersOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(playersOverview);

            // Give the controller access to the main app.
            PlayersOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*******************************************************************************************************************
     * Shows the Teams overview inside the root layout.
     ******************************************************************************************************************/
    public void showTeamsOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/TeamsOverview.fxml"));
            AnchorPane teamsOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(teamsOverview);

            // Give the controller access to the main app.
            TeamsOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import view.PlayersOverviewController;
import view.RootLayoutController;
import view.TeamsOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    
	private static ObservableList<Player> players = FXCollections.observableArrayList();
	private static ObservableList<Team> teams = FXCollections.observableArrayList();
	
	public static void main(String[] args) {
        
        //Scanner reads csv file with player stats
        try {
        Scanner input = new Scanner(new File("data.csv"));

        //Builds an arrayList of all the players with their info
        //Gets player info and creates new player
        while (input.hasNextLine()) {
        	String[] s1 = input.nextLine().split(",");
        	players.add(new Player(s1[0], s1[1], s1[2], Double.valueOf(s1[3]), Double.valueOf(s1[4]), Double.valueOf(s1[5]), Double.valueOf(s1[6]), Double.valueOf(s1[7]), Double.valueOf(s1[8]), Double.valueOf(s1[9])));
    	  	}
        }
        catch(FileNotFoundException fnfe) {
        	System.err.println(fnfe.getMessage());
        }
      
        //Builds an arrayList of teams with their city name
		ArrayList<String> teamCity = new ArrayList<String>();
		
		for (int i = 0; i < players.size(); i++) 
		{
			//If Team isn't on Teams list, add it
			if (!(teamCity.contains(players.get(i).getTeam()))) 
			{
				teamCity.add(players.get(i).getTeam());
				Team team = new Team();
				team.setCity(players.get(i).getTeam());
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
      		//Display Info for Teams or Players
      		displayPlayer();
      		//displayTeams();
            launch(args);
	}
	
	// displayPlayer(): Prints all Player info ---------------------------
	public static void displayPlayer() {
//		Collections.sort(p1);//Sorts by Player name
		System.out.println("Number of Players: " + players.size());
		System.out.printf("%-25s%-19s%-19s\n", "Name:", "Team:", "Position:");
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).toString());
		}
	}
	
	//displayTeams(): Print Team Roster ---------------------------------
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
	
	public ObservableList<Player> getPlayerData(){
		return players;
	}
	
	public ObservableList<Team> getTeamData(){
		return teams;
	}
	
	public MainApp()
	{
		
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Basketball Statistics");

        initRootLayout();	
        
        showPlayersOverview();
	}
	
	/**
     * Initializes the root layout
     */
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
    
    /**
     * Shows the Players overview inside the root layout.
     */
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
    
    /**
     * Shows the Teams overview inside the root layout.
     */
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
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}

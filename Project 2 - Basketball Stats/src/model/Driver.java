package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Driver {
	
	//Variables -----------------------------------------------------
	public static ObservableList<Player> players = FXCollections.observableArrayList();
	public static ObservableList<Team> teams = FXCollections.observableArrayList();
	
	//Main -------------------------------------------------------------------------------------------------
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner reads csv file with player stats
		Scanner input = new Scanner(new File("data.csv"));
		
		//Builds PLayer and Teams lists
		buildPLayerList(input);
		buildTeam();
		
		//Display Info for Teams or Players
		displayPlayer();
		//displayTeams();

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
	
	//buildPLayerList --------------------------------------------------------------------------------------
	//Builds an arrayList of all the players with their info
	public static void buildPLayerList(Scanner input) {
		//Gets player info and creates new player
		
		while (input.hasNextLine()) {
			String[] s1 = input.nextLine().split(",");
				players.add(new Player(s1[0], s1[1], s1[2], Double.valueOf(s1[3]), Double.valueOf(s1[4]), Double.valueOf(s1[5]), Double.valueOf(s1[6]), Double.valueOf(s1[7]), Double.valueOf(s1[8]), Double.valueOf(s1[9])));
			}
	} 
	
	//buildTeam --------------------------------------------------------------------------------------------
	//Builds an arrayList of teams with their city name
	public static void buildTeam() {
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
	}
}

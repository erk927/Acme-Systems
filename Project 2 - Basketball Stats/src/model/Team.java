package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;

public class Team {
	// Variables
	private StringProperty city;
	private IntegerProperty numOfPlayers;
	private DoubleProperty appg, arpg, aapg, aspg, abpg, atpg, aage;

	ObservableList<Player> roster = FXCollections.observableArrayList();;

	//Default Constructor
	Team(){
		this.city = new SimpleStringProperty("Unknown");
		this.numOfPlayers = new SimpleIntegerProperty(0);
		this.aage = new SimpleDoubleProperty(0.0);
		this.appg = new SimpleDoubleProperty(0.0); //average points per game
		this.arpg = new SimpleDoubleProperty(0.0); //average rebounds per game
		this.aapg = new SimpleDoubleProperty(0.0); //average assists per game
		this.aspg = new SimpleDoubleProperty(0.0); //average steals per game
		this.abpg = new SimpleDoubleProperty(0.0); //average block per game
		this.atpg = new SimpleDoubleProperty(0.0); //average turnovers per game
		roster.addListener(new RosterUpdateListener(this));
	}
	
	//Overloaded Constructor: Takes team Name
	public Team(String city) {
		this.city = new SimpleStringProperty(city);
		this.numOfPlayers = new SimpleIntegerProperty(0);
	}
	
	//Overloaded Constructors
	Team(String city, int numPlayers){
		this.city = new SimpleStringProperty(city);
		this.numOfPlayers = new SimpleIntegerProperty(numPlayers);
		this.aage = new SimpleDoubleProperty(0.0);
		this.appg = new SimpleDoubleProperty(0.0); //average points per game
		this.arpg = new SimpleDoubleProperty(0.0); //average rebounds per game
		this.aapg = new SimpleDoubleProperty(0.0); //average assists per game
		this.aspg = new SimpleDoubleProperty(0.0); //average steals per game
		this.abpg = new SimpleDoubleProperty(0.0); //average block per game
		this.atpg = new SimpleDoubleProperty(0.0); //average turnovers per game
		roster.addListener(new RosterUpdateListener(this));
	}
	
	Team(String city, ObservableList<Player> roster){
		this.city = new SimpleStringProperty(city);
		this.numOfPlayers = new SimpleIntegerProperty(roster.size());
		this.roster = roster;
		double avgage = 0;
		double avgppg = 0;
		double avgrpg = 0;
		double avgapg = 0;
		double avgspg = 0;
		double avgbpg = 0;
		double avgtpg = 0;
		for(Player p : roster)
		{
			avgage += p.getAge();
			avgppg += p.getPpg();
			avgrpg += p.getRpg();
			avgapg += p.getApg();
			avgspg += p.getSpg();
			avgbpg += p.getBpg();
			avgtpg += p.getTpg();
		}
		this.aage = new SimpleDoubleProperty(avgage / roster.size());
		this.appg = new SimpleDoubleProperty(avgppg / roster.size()); //average points per game
		this.arpg = new SimpleDoubleProperty(avgrpg / roster.size()); //average rebounds per game
		this.aapg = new SimpleDoubleProperty(avgapg / roster.size()); //average assists per game
		this.aspg = new SimpleDoubleProperty(avgspg / roster.size()); //average steals per game
		this.abpg = new SimpleDoubleProperty(avgbpg / roster.size()); //average block per game
		this.atpg = new SimpleDoubleProperty(avgtpg / roster.size()); //average turnovers per game
		roster.addListener(new RosterUpdateListener(this));
	}
	
	//Copies a Team, without changes to this one applying to the original.  Useful for the 'compare' GUI
	public Team(Team t){
		this.city = new SimpleStringProperty(t.getCity());
		this.numOfPlayers = new SimpleIntegerProperty(t.getRoster().size());
		double avgage = 0;
		double avgppg = 0;
		double avgrpg = 0;
		double avgapg = 0;
		double avgspg = 0;
		double avgbpg = 0;
		double avgtpg = 0;
		for(Player p : t.getRoster())
		{
			avgage += p.getAge();
			avgppg += p.getPpg();
			avgrpg += p.getRpg();
			avgapg += p.getApg();
			avgspg += p.getSpg();
			avgbpg += p.getBpg();
			avgtpg += p.getTpg();
			Player player = new Player(p);
			this.roster.add(player);
		}
		this.aage = new SimpleDoubleProperty(avgage / roster.size());
		this.appg = new SimpleDoubleProperty(avgppg / roster.size()); //average points per game
		this.arpg = new SimpleDoubleProperty(avgrpg / roster.size()); //average rebounds per game
		this.aapg = new SimpleDoubleProperty(avgapg / roster.size()); //average assists per game
		this.aspg = new SimpleDoubleProperty(avgspg / roster.size()); //average steals per game
		this.abpg = new SimpleDoubleProperty(avgbpg / roster.size()); //average block per game
		this.atpg = new SimpleDoubleProperty(avgtpg / roster.size()); //average turnovers per game
		roster.addListener(new RosterUpdateListener(this));
	}
	
	public void setCity(StringProperty city) {
		this.city = city;
	}

	public void setNumOfPlayers(Integer numOfPlayers) {
		this.numOfPlayers.set(numOfPlayers);
	}

	public void setAppg(Double appg) {
		this.appg.set(appg);
	}

	public void setArpg(Double arpg) {
		this.arpg.set(arpg);
	}

	public void setAapg(Double aapg) {
		this.aapg.set(aapg);
	}

	public void setAspg(Double aspg) {
		this.aspg.set(aspg);
	}

	public void setAbpg(Double abpg) {
		this.abpg.set(abpg);
	}

	public void setAtpg(Double atpg) {
		this.atpg.set(atpg);
	}

	public void setAage(Double aage) {
		this.aage.set(aage);
	}

	public String getCity() {
		return city.get();
	}
	
	public StringProperty getCityProperty() {
		return city;
	}

	public int getNumOfPlayers() {
		return numOfPlayers.get();
	}
	
	public IntegerProperty getNumOfPlayersProperty() {
		return numOfPlayers;
	}

	public void setCity(String city) {
		this.city.set(city);
	}
	
	public Double getAppg() {
		return appg.get();
	}
	
	public DoubleProperty getAppgProperty() {
		return appg;
	}

	public Double getArpg() {
		return arpg.get();
	}
	
	public DoubleProperty getArpgProperty() {
		return arpg;
	}
	
	public Double getAapg() {
		return aapg.get();
	}
	
	public DoubleProperty getAapgProperty() {
		return aapg;
	}
	
	public Double getAspg() {
		return aspg.get();
	}
	
	public DoubleProperty getAspgProperty() {
		return aspg;
	}
	
	public Double getAbpg() {
		return abpg.get();
	}
	
	public DoubleProperty getAbpgProperty() {
		return abpg;
	}
	
	public Double getAtpg() {
		return atpg.get();
	}
	
	public DoubleProperty getAtpgProperty() {
		return atpg;
	}
	
	public Double getAage() {
		return aage.get();
	}
	
	public DoubleProperty getAageProperty() {
		return aage;
	}
	
	public ObservableList<Player> getRoster() {
		return roster;
	}

	public void setRoster(ObservableList<Player> roster) {
		this.roster = roster;
	}
}

class RosterUpdateListener implements ListChangeListener<Player>{
	Team t;
	public RosterUpdateListener(Team t) {
		this.t = t;
	}
	@Override
	public void onChanged(Change<? extends Player> c) {
		// TODO Auto-generated method stub
		double avgage = 0;
		double avgppg = 0;
		double avgrpg = 0;
		double avgapg = 0;
		double avgspg = 0;
		double avgbpg = 0;
		double avgtpg = 0;
		for(Player p : t.getRoster())
		{
			avgage += p.getAge();
			avgppg += p.getPpg();
			avgrpg += p.getRpg();
			avgapg += p.getApg();
			avgspg += p.getSpg();
			avgbpg += p.getBpg();
			avgtpg += p.getTpg();
		}
		t.setAage(avgage / t.getRoster().size());
		t.setAppg(avgppg / t.getRoster().size()); //average points per game
		t.setArpg(avgrpg / t.getRoster().size()); //average rebounds per game
		t.setAapg(avgapg / t.getRoster().size()); //average assists per game
		t.setAspg(avgspg / t.getRoster().size()); //average steals per game
		t.setAbpg(avgbpg / t.getRoster().size()); //average block per game
		t.setAtpg(avgtpg / t.getRoster().size()); //average turnovers per game
		t.setNumOfPlayers(t.getRoster().size());
	}
}

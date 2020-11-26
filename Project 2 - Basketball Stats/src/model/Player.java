package model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player implements Comparable<Player>{
	
	//***** Variables
	StringProperty name, team, pos;
	DoubleProperty ppg, rpg, apg, spg, bpg, tpg, age;

	
	//Default Constructor
	public Player(){
		this.name = new SimpleStringProperty("Unknown");
		this.team = new SimpleStringProperty("Unknown");
		this.pos = new SimpleStringProperty("Unknown");
		this.age = new SimpleDoubleProperty(0.0);
		this.ppg = new SimpleDoubleProperty(0.0); //points per game
		this.rpg = new SimpleDoubleProperty(0.0); //rebounds per game
		this.apg = new SimpleDoubleProperty(0.0); //assists per game
		this.spg = new SimpleDoubleProperty(0.0); //steals per game
		this.bpg = new SimpleDoubleProperty(0.0); //block per game
		this.tpg = new SimpleDoubleProperty(0.0); //turnovers per game
	}
	
	//Overloaded Constructor
	public Player(String name, String team, String pos, Double age, Double ppg, Double rpg, Double apg, Double spg, Double bpg, Double tpg){
		this.name = new SimpleStringProperty(name);
		this.team = new SimpleStringProperty(team);
		this.pos = new SimpleStringProperty(pos);
		this.age = new SimpleDoubleProperty(age);
		this.ppg = new SimpleDoubleProperty(ppg);
		this.rpg = new SimpleDoubleProperty(rpg);
		this.apg = new SimpleDoubleProperty(apg);
		this.spg = new SimpleDoubleProperty(spg);
		this.bpg = new SimpleDoubleProperty(bpg);
		this.tpg = new SimpleDoubleProperty(tpg);
	}
	
	//Overloaded Constructor
	public Player(Player p)
	{
		this.name = new SimpleStringProperty(p.getName());
		this.team = new SimpleStringProperty(p.getTeam());
		this.pos = new SimpleStringProperty(p.getPos());
		this.age = new SimpleDoubleProperty(p.getAge());
		this.ppg = new SimpleDoubleProperty(p.getPpg());
		this.rpg = new SimpleDoubleProperty(p.getRpg());
		this.apg = new SimpleDoubleProperty(p.getApg());
		this.spg = new SimpleDoubleProperty(p.getSpg());
		this.bpg = new SimpleDoubleProperty(p.getBpg());
		this.tpg = new SimpleDoubleProperty(p.getTpg());
	}

	// Getters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String getName() {
		return name.get();
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public String getTeam() {
		return team.get();
	}
	
	public StringProperty teamProperty() {
		return team;
	}

	public String getPos() {
		return pos.get();
	}
	
	public StringProperty posProperty() {
		return pos;
	}

	public Double getAge() {
		return age.get();
	}
	
	public DoubleProperty ageProperty() {
		return age;
	}

	public Double getPpg() {
		return ppg.get();
	}
	
	public DoubleProperty ppgProperty() {
		return ppg;
	}

	public Double getRpg() {
		return rpg.get();
	}
	
	public DoubleProperty rpgProperty() {
		return rpg;
	}

	public Double getApg() {
		return apg.get();
	}
	
	public DoubleProperty apgProperty() {
		return apg;
	}

	public Double getSpg() {
		return spg.get();
	}
	
	public DoubleProperty spgProperty() {
		return spg;
	}

	public Double getBpg() {
		return bpg.get();
	}
	
	public DoubleProperty bpgProperty() {
		return bpg;
	}

	public Double getTpg() {
		return tpg.get();
	}
	
	public DoubleProperty tpgProperty() {
		return tpg;
	}
	
	
	//Setters ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public void setName(String name) {
		this.name.set(name);
	}

	public void setTeam(String team) {
		this.team.set(team);
	}

	public void setPos(String pos) {
		this.pos.set(pos);
	}

	public void setAge(Double age) {
		this.age.set(age);
	}

	public void setPpg(Double ppg) {
		this.ppg.set(ppg);
	}

	public void setRpg(Double rpg) {
		this.rpg.set(rpg);
	}

	public void setApg(Double apg) {
		this.apg.set(apg);
	}

	public void setSpg(Double spg) {
		this.spg.set(spg);
	}

	public void setBpg(Double bpg) {
		this.bpg.set(bpg);
	}

	public void setTpg(Double tpg) {
		this.tpg.set(tpg);
	}

	@Override
	public int compareTo(Player p) {
		return getName().compareTo(p.getName());
	}
	
	//ToString
	public String toString() {
		System.out.printf("%-25s%-19s%-19s", getName(), getTeam(), getPos());
		return "";
	}
}

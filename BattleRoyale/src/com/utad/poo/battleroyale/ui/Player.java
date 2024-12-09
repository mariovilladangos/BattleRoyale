package com.utad.poo.battleroyale.ui;

public class Player {
	
	protected String name;
	protected String className;
	protected Integer health;
	public static final String DEF_NAME="Player";
	public static final String DEF_CLASS_NAME="Prisoner";
	
	public Player(){
		
	}
	public Player(String name,String className,Integer health){
		this.health=health;
		this.name=name;
		this.className=className;
	}



	public String getName() {
		return this.name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getClassName() {
		return this.className;
	}



	public void setClassName(String className) {
		this.className = className;
	}



	public Integer getHealth() {
		return this.health;
	}



	public void setHealth(Integer health) {
		this.health = health;
	}



	@Override
	public String toString() {
		return "Player [name= " + this.name + ", class= " + this.className  + ", health= " + this.className + " ]";
	}
	
	

}

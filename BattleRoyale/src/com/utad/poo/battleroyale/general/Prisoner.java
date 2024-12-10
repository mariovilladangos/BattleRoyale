package com.utad.poo.battleroyale.general;

public class Prisoner extends Player{
	
	public static final Integer PRISONER_HEALTH=120;
	public static final String CLASS_NAME="Prisoner";
	
	
	public Prisoner(String name,Weapon weapon){
		super(name,weapon);
	}


	public Integer getPrisonerHealth() {
		return PRISONER_HEALTH;
	}


	public String getClassName() {
		return CLASS_NAME;
	}


	@Override
	public String toString() {
		return super.toString()+ " ClassName=" + this.getClassName() + " Health="+ this.getPrisonerHealth();
	}
	
	
}

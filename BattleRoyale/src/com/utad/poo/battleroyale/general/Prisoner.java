package com.utad.poo.battleroyale.general;

public class Prisoner extends Player{
	
	public static final Integer PRISONER_HEALTH=100;
	public static final String CLASS_NAME="Prisoner";
	
	
	public Prisoner(String name,String weapon){
		super(name,weapon);
	}


	public static Integer getPrisonerHealth() {
		return PRISONER_HEALTH;
	}


	public static String getClassName() {
		return CLASS_NAME;
	}


	@Override
	public String toString() {
		return super.toString()+ "ClassName= " + this.getClassName() + "Health= "+ this.getPrisonerHealth();
	}
	
	
}

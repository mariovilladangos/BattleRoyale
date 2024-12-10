package com.utad.poo.battleroyale.general;

public class Prisoner extends Player{
	
	public static final Integer PRISONER_HEALTH=120;
	public static final String CLASS_NAME="Prisoner";
	
	
	public Prisoner(String name,Weapon weapon){
		super(name,weapon, Prisoner.PRISONER_HEALTH);
	}



	@Override
	public String toString() {
		return super.toString()+ " ClassName=" + Prisoner.CLASS_NAME + " ]";
	}
}

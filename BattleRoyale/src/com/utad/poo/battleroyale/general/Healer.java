package com.utad.poo.battleroyale.general;

public class Healer extends Player {
	
	public static final Integer HEALER_HEALTH=100;
	public static final String CLASS_NAME="Healer";
	
	public static final Integer[] DEF_PROB = {50,25,25};
	//{Prob. de cura, Prob. de mejora, Prob. de nada}
	
	public Healer(String name, Weapon weapon) {
		super(name, weapon, Healer.HEALER_HEALTH, Healer.DEF_PROB);
	}


	@Override
	public String toString() {
		return super.toString()+" ClassName=" + Healer.CLASS_NAME;
	}
	
	
	
	
}

package com.utad.poo.battleroyale.general;

public class Healer extends Player {
	
	public static final Integer HEALER_HEALTH=100;
	public static final String CLASS_NAME="Healer";
	
	public static final Integer[] DEF_PROB = {50,25,25};
	//{Prob. de cura, Prob. de mejora, Prob. de nada}
	
	public Healer(String name, Weapon weapon) {
		super(name, weapon, HEALER_HEALTH, DEF_PROB);
	}

	public  Integer getHealerHealth() {
		return HEALER_HEALTH;
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String toString() {
		return super.toString()+" ClassName=" + this.getClassName() +" Healer=" + this.getHealerHealth();
	}
	
	
	
	
}

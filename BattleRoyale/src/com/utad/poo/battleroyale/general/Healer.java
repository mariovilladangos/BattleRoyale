package com.utad.poo.battleroyale.general;

public class Healer extends Player {
	
	public static final Integer HEALER_HEALTH=100;
	public static final String CLASS_NAME="Healer";
	
	public Healer(String name, Weapon weapon) {
		super(name, weapon);
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

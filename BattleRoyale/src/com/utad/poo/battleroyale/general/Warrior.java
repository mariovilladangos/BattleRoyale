package com.utad.poo.battleroyale.general;

public class Warrior extends Player {
	
	public static final Integer WARRIOR_HEALTH=150;
	public static final String CLASS_NAME="Warrior";
	
	public static final Integer[] DEF_PROB = {25,50,25};
	//{Prob. de cura, Prob. de mejora, Prob. de nada}
	
	public Warrior(String name, Weapon weapon) {
		super(name, weapon, WARRIOR_HEALTH, DEF_PROB);
		
	}

	public Integer getWarriorHealth() {
		return WARRIOR_HEALTH;
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String toString() {
		return super.toString() + " Classname=" + this.getClassName() + " Health=" + this.getWarriorHealth();
	}

		
}

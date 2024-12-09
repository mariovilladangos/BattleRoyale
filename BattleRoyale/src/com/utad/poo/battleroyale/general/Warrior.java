package com.utad.poo.battleroyale.general;

public class Warrior extends Player {
	
	public static final Integer WARRIOR_HEALTH=150;
	public static final String CLASS_NAME="Warrior";
	
	public Warrior(String name, Weapon weapon) {
		super(name, weapon);
		
	}

	public Integer getWarriorHealth() {
		return WARRIOR_HEALTH;
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String toString() {
		return super.toString()+"Classname= "+ this.getClassName()+"Health= "+this.getWarriorHealth();
	}

		
}

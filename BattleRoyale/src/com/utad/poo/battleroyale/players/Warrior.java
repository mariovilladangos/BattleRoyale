package com.utad.poo.battleroyale.players;

import com.utad.poo.battleroyale.weapons.Weapon;

public final class Warrior extends Player {
	
	public static final Integer WARRIOR_HEALTH=150;
	public static final String CLASS_NAME="Warrior";
	
	public static final Integer[] DEF_PROB = {25,50,25};
	//{Prob. de cura, Prob. de mejora, Prob. de nada}
	
	public Warrior(String name, Weapon weapon) {
		super(name, weapon, Warrior.WARRIOR_HEALTH, Warrior.DEF_PROB);
		
	}
	
	@Override
	public String getClassType() {
		return "Guerrero";
	}

	@Override
	public String toString() {
		return super.toString() + " Classname=" + Warrior.CLASS_NAME +" ]";
	}

		
}

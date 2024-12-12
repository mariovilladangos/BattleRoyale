package com.utad.poo.battleroyale.checks;
import com.utad.poo.battleroyale.general.*;
import com.utad.poo.battleroyale.weapons.Spear;

import com.utad.poo.battleroyale.players.Player;
import com.utad.poo.battleroyale.players.Warrior;

public class Prueba {
	public static void main(String[] args) {
		Spear spear = new Spear();
		Player guerrero = new Warrior("Juan", spear);
		
		System.out.print(guerrero);
	}
}

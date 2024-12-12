package com.utad.poo.battleroyale.weapons;

import java.util.Arrays;

import com.utad.poo.battleroyale.ui.*;

public class Weapon {
	
	protected Integer[] damage;
	protected Integer level;
	
	//public static final Double DEF_DAMAGE = 10.0;
	public static final Integer DEF_LEVEL = 1;
	public static final Integer MAX_LEVEL = 3;
	
	//Constructor
	public Weapon(Integer[] damage) {
		this(damage, DEF_LEVEL);
	}
	public Weapon(Integer[] damage, Integer level) {
		this.damage = damage;
		this.level = level;
	}
	
	//Getters y setters
	public Integer[] getDamage() {
		return damage;
	}
	public void setDamage(Integer[] damage) {
		this.damage = damage;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getWeaponType() {
		return "[-]";
	}
	
	//ToString
	@Override
	public String toString() {
		return "Weapon [damage=" + Arrays.toString(damage) + ", level=" + level + "]";
	}
	
	//Método para mejorar el arma
	public void upgrade(String playerName) {
		System.out.println("\n" + playerName.toUpperCase() + " ha encontrado una piedra de mejora");
    	if (this.level < MAX_LEVEL) {
    		this.level += 1;
        	System.out.println("  💎 " + playerName + " ha mejorado el arma");
    	}
    	else System.out.println("  🚫 Pero " + playerName + " ya tiene su arma a nivel máximo");
    }
	public void upgrade(GameMenu game, String playerName) {
		game.addTerminalLine("\n" + playerName.toUpperCase() + " ha encontrado una piedra de mejora");
    	if (this.level < MAX_LEVEL) {
    		this.level += 1;
    		game.addTerminalLine("  💎 " + playerName + " ha mejorado el arma");
    	}
    	else{
    		game.addTerminalLine("  🚫 Pero " + playerName + " ya tiene su arma a nivel máximo");
    	}
    }
}

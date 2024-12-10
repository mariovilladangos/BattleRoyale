package com.utad.poo.battleroyale.general;

import java.util.Arrays;

public class Weapon {
	
	protected Integer[] damage;
	protected Integer level;
	
	//public static final Double DEF_DAMAGE = 10.0;
	public static final Integer DEF_LEVEL = 1;
	
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
	public void upgrade() {
    	this.level += 1;
    }
}

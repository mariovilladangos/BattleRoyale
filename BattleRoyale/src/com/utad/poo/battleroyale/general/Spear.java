package com.utad.poo.battleroyale.general;

public class Spear extends Weapon{
	
	
	//public static final Integer DEF_LEVEL = 1;
	public static final Integer[] DEF_DAMAGE = {20, 40, 70};	
	public Spear() {
		this(DEF_DAMAGE);
	}
	public Spear (Integer[] damage) {
		super(damage);
	}
	//Getters and setters
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
	@Override
	public String toString() {
		return "Spear damage=" + damage + ", level=" + level + " ";
	}
	
}

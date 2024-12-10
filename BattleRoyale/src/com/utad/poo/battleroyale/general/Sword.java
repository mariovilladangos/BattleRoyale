package com.utad.poo.battleroyale.general;

public class Sword extends Weapon{
	
	
	//public static final Integer DEF_LEVEL = 1;
	//public static final Double[] DEF_DAMAGE = {}
	public static final Integer[] DEF_DAMAGE = {30, 50, 65};
	
	public Sword() {
		this(DEF_DAMAGE);
	}
	public Sword (Integer[] damage) {
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
		return "Sword damage=" + damage + ", level=" + level + " ";
	}
	
	
	
}
